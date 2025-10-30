document.getElementById('loginForm').addEventListener('submit', handleLogin);

async function handleLogin(event) {
    event.preventDefault();

    const form = event.target;
    const messageDiv = document.getElementById('message');
    
    const credentials = {
        username: form.username.value,
        password: form.password.value
    };

    messageDiv.textContent = 'Memproses login...';
    messageDiv.style.color = '#007bff';

    try {
        // Kirim data ke endpoint login Spring Boot
        const response = await fetch('http://localhost:8080/api/users/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(credentials)
        });

        const responseData = await response.json();
        
        if (response.ok) {
            // Login berhasil (HTTP Status 200 OK)
            localStorage.setItem('loggedInUserId', responseData.userId);
            messageDiv.textContent = responseData.message;
            messageDiv.style.color = 'green';
            
            // Redirect
            setTimeout(() => {
                 window.location.href = '/profile'; 
            }, 1500); 
            
        } else {
            // Login gagal (HTTP Status 401 UNAUTHORIZED)
            messageDiv.textContent = `Login Gagal: ${responseData.message}`;
            messageDiv.style.color = 'red';
        }
        
    } catch (error) {
        console.error('Network Error:', error);
        messageDiv.textContent = 'Gagal terhubung ke server. Pastikan aplikasi berjalan.';
        messageDiv.style.color = 'red';
    }
}