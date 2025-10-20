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

            // Respons dari server (misal: "Login successful..." atau "Invalid...")
            const responseText = await response.text();
            
            if (response.ok) {
                // Login berhasil
                messageDiv.textContent = responseText;
                messageDiv.style.color = 'green';
                
                // Opsional: Redirect ke halaman dashboard atau simpan token
                // window.location.href = '/dashboard'; 
            } else {
                // Login gagal
                messageDiv.textContent = `Login Gagal: ${responseText}`;
                messageDiv.style.color = 'red';
            }
            
        } catch (error) {
            console.error('Network Error:', error);
            messageDiv.textContent = 'Gagal terhubung ke server. Pastikan aplikasi berjalan.';
            messageDiv.style.color = 'red';
        }
    }