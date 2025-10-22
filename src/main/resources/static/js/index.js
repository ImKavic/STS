document.getElementById('loginForm').addEventListener('submit', handleLogin);

// Fungsi untuk menangani proses Login
async function handleLogin(event) {
    event.preventDefault(); // Mencegah form reload halaman

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

        const responseText = await response.text();
        
        if (response.ok) {
            // Login berhasil (HTTP Status 200-299)
            messageDiv.textContent = responseText;
            messageDiv.style.color = 'green';
            
            // --- LOGIKA REDIRECT BARU ---
            // Redirect ke halaman profil setelah berhasil login
            setTimeout(() => {
                 window.location.href = '/profile'; 
            }, 1500); // Tunda 1.5 detik agar user sempat melihat pesan sukses
            
        } else {
            // Login gagal (HTTP Status 4xx, 5xx, atau respons.ok=false)
            messageDiv.textContent = `Login Gagal: ${responseText}`;
            messageDiv.style.color = 'red';
        }
        
    } catch (error) {
        console.error('Network Error:', error);
        messageDiv.textContent = 'Gagal terhubung ke server. Pastikan aplikasi berjalan.';
        messageDiv.style.color = 'red';
    }
}