let map;
let markers = [];
let infoWindow;
let allUsers = [];
let allUsersWithLocation = [];

function initMap() {
    map = new google.maps.Map(document.getElementById("map"), {
        center: { lat: 42.698334, lng: 23.319941 },
        zoom: 12,
        streetViewControl: false,
        mapTypeControl: true,
        fullscreenControl: true
    });

    infoWindow = new google.maps.InfoWindow();
    loadUsers();
}

function loadUsers() {
    fetch('http://localhost:8080/users')
        .then(response => {
            if (!response.ok) throw new Error('Неуспешно зареждане на потребители');
            return response.json();
        })
        .then(users => {
            allUsers = users;
            renderUserList(users);
            markAllUsersWithLocation(); 
        })
        .catch(error => {
            console.error("Грешка при зареждане на потребители:", error);
            Swal.fire({
                icon: 'error',
                title: 'Грешка',
                text: 'Неуспешно зареждане на списък с потребители',
                confirmButtonText: 'OK'
            });
        });
}

function renderUserList(users) {
    const userListContainer = document.getElementById('userList');
    userListContainer.innerHTML = '';

    users.forEach(user => {
        const userElement = document.createElement('div');
        userElement.className = 'user-item';
        userElement.innerHTML = `
            <div class="form-check">
                <input class="form-check-input user-checkbox" type="checkbox" value="${user.id}" id="user-${user.id}">
                <label class="form-check-label" for="user-${user.id}">
                    <div>
                        <strong>${user.username}</strong><br>
                        <small class="text-muted">Тел: ${user.number}</small>
                    </div>
                </label>
            </div>
        `;
        userListContainer.appendChild(userElement);
    });

    document.querySelectorAll('.user-checkbox').forEach(checkbox => {
        checkbox.addEventListener('change', updateSelectedCount);
    });
}

function updateSelectedCount() {
    const selectedCount = document.querySelectorAll('.user-checkbox:checked').length;
    document.getElementById('selectedCount').textContent = `${selectedCount} избрани`;
}

function showSelectedUsers() {
    const selectedCheckboxes = document.querySelectorAll('.user-checkbox:checked');

    if (selectedCheckboxes.length === 0) {
        Swal.fire({
            icon: 'warning',
            title: 'Няма избрани потребители',
            text: 'Моля, изберете поне един потребител',
            confirmButtonText: 'OK'
        });
        return;
    }

    const selectedUserIds = Array.from(selectedCheckboxes).map(cb => parseInt(cb.value));
    clearMarkers();

    Promise.all(selectedUserIds.map(userId =>
        fetch(`http://localhost:8080/geolocation/user?userId=${userId}`)
            .then(response => {
                if (!response.ok) throw new Error(`Грешка при зареждане на данни за потребител ${userId}`);
                return response.json();
            })
    ))
    .then(results => {
        const allLocations = results.flat();
        if (allLocations.length === 0) {
            Swal.fire({
                icon: 'info',
                title: 'Няма данни',
                text: 'Не са намерени локации за избраните потребители',
                confirmButtonText: 'OK'
            });
            return;
        }

        const bounds = new google.maps.LatLngBounds();
        const colors = ['red', 'blue', 'green', 'yellow', 'purple', 'orange', 'pink', 'brown', 'gray', 'black'];

        allLocations.forEach((location, index) => {
            const userIndex = selectedUserIds.indexOf(location.user.id);
            const userColor = colors[userIndex % colors.length];
            const user = allUsers.find(u => u.id === location.user.id);
            const username = user ? user.username : `#${location.user.id}`;

            const marker = new google.maps.Marker({
                position: { lat: location.latitude, lng: location.longitude },
                map: map,
                title: `Потребител: ${username}`,
                icon: {
                   url: `http://maps.google.com/mapfiles/ms/icons/${userColor}-dot.png`
                }
            });

            markers.push(marker);
            bounds.extend(marker.getPosition());

            marker.addListener('click', () => {
                infoWindow.setContent(`
                    <div style="min-width: 200px">
                        <h6>${user ? `${user.firstName} ${user.lastName}` : `Потребител #${location.user.id}`}</h6>
                        <p><strong>Дата:</strong> ${new Date(location.createDate).toLocaleString()}</p>
                        <p><strong>Координати:</strong> ${location.latitude.toFixed(4)}, ${location.longitude.toFixed(4)}</p>
                    </div>
                `);
                infoWindow.open(map, marker);
            });
        });

        map.fitBounds(bounds);
        if (bounds.getNorthEast().equals(bounds.getSouthWest())) {
            map.setZoom(10);
            map.setCenter(bounds.getCenter());
        }
    })
    .catch(error => {
        console.error("Грешка:", error);
        Swal.fire({
            icon: 'error',
            title: 'Грешка',
            text: error.message || 'Възникна грешка при зареждане на локации',
            confirmButtonText: 'OK'
        });
    });
}

function clearMarkers() {
    markers.forEach(marker => marker.setMap(null));
    markers = [];
    infoWindow.close();
}

function markAllUsers() {
    const checkboxes = document.querySelectorAll('.user-checkbox');
    checkboxes.forEach(checkbox => checkbox.checked = true);
    updateSelectedCount();
}

function unmarkAllUsers() {
    const checkboxes = document.querySelectorAll('.user-checkbox');
    checkboxes.forEach(checkbox => checkbox.checked = false);
    updateSelectedCount();
}

function markAllUsersWithLocation() {
    // Проверяваме дали потребителите са заредени
    if (allUsers.length === 0) {
        setTimeout(markAllUsersWithLocation, 100);
        return;
    }

    fetch('http://localhost:8080/users/withLocations')
        .then(response => {
            if (!response.ok) throw new Error('Неуспешно зареждане на потребители с локации');
            return response.json();
        })
        .then(usersWithLocations => {
            allUsersWithLocation = usersWithLocations;

            document.querySelectorAll('.user-checkbox').forEach(checkbox => {
                const userId = parseInt(checkbox.value);
                const hasLocation = usersWithLocations.some(user => user.id === userId);
                checkbox.checked = hasLocation;
            });

            updateSelectedCount();
            showSelectedUsers(); // Автоматично показване на маркерите
        })
        .catch(error => {
            console.error("Грешка при зареждане на потребители с локации:", error);
            Swal.fire({
                icon: 'error',
                title: 'Грешка',
                text: 'Неуспешно зареждане на списък с потребители с локации',
                confirmButtonText: 'OK'
            });
        });
}

// Автоматично обновяване на маркерите всеки 3 секунди
setInterval(showSelectedUsers, 3000);

// Глобални функции за достъп от HTML
window.initMap = initMap;
window.showSelectedUsers = showSelectedUsers;
window.markAllUsers = markAllUsers;
window.unmarkAllUsers = unmarkAllUsers;