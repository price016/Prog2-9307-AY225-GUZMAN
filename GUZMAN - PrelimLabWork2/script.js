const validCredentials = {
    'Gabriel': 'Ali123',
    'Guzman': 'Iya123',
    'Sir Fabregas': 'Fab123'
    };

let attendanceRecords = [];

function playBeep() {
	const audio = document.getElementById('beep');
	if (audio) {
		audio.play().catch(() => {
			try {
				const ctx = new (window.AudioContext || window.webkitAudioContext)();
				const o = ctx.createOscillator();
				const g = ctx.createGain();
				o.type = 'sine';
				o.frequency.value = 800;
				o.connect(g);
				g.connect(ctx.destination);
				o.start();
				g.gain.exponentialRampToValueAtTime(0.0001, ctx.currentTime + 0.15);
				o.stop(ctx.currentTime + 0.16);
			} catch (e) {
				console.log('\u0007');
			}
		});
	}
}

function formatTimestamp(date) {
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');
        const year = date.getFullYear();
        const hours = String(date.getHours()).padStart(2, '0');
        const minutes = String(date.getMinutes()).padStart(2, '0');
        const seconds = String(date.getSeconds()).padStart(2, '0');
            
        return `${month}/${day}/${year} ${hours}:${minutes}:${seconds}`;
        }

function updateAttendanceDisplay() {
        const recordsDiv = document.getElementById('attendanceRecords');
        recordsDiv.innerHTML = '';
            
        const recentRecords = attendanceRecords.slice(-5).reverse();
        recentRecords.forEach(record => {
        const item = document.createElement('div');
            item.className = 'attendance-item';
            item.textContent = `${record.username} (${record.section || record.sectionOccupation || 'N/A'}) - ${record.timestamp}`;
            recordsDiv.appendChild(item);
            });
        }

        document.getElementById('login').addEventListener('submit', function(e) {
            e.preventDefault();
            
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;
        const sectionOccupation = document.getElementById('sectionOccupation') ? document.getElementById('sectionOccupation').value.trim() : '';
        const errorMessage = document.getElementById('errorMessage');
            
            if (validCredentials[username] && validCredentials[username] === password) {
                const currentTime = new Date();
                const timestamp = formatTimestamp(currentTime);
                
                attendanceRecords.push({
                    username: username,
                    sectionOccupation: sectionOccupation,
                    timestamp: timestamp
                });
                
                document.getElementById('loginForm').style.display = 'none';
                
                const welcomeSection = document.getElementById('welcomeSection');
                welcomeSection.style.display = 'block';
                
                document.getElementById('welcomeMessage').textContent = `Welcome, ${username}! 🎉`;
                
                document.getElementById('timestampDisplay').innerHTML = `
                    <strong>Login Time:</strong><br>${timestamp}
                `;
                
                updateAttendanceDisplay();
                generateClassificationSummary();
                
                document.getElementById('login').reset();
                errorMessage.style.display = 'none';
            } else {
                playBeep();
                errorMessage.style.display = 'block';
                
                errorMessage.style.animation = 'none';
                setTimeout(() => {
                    errorMessage.style.animation = 'shake 0.5s';
                }, 10);
            }
        });

        function downloadAttendance() {
            let attendanceData = '=== ATTENDANCE SUMMARY ===\n\n';
            attendanceData += `Generated on: ${formatTimestamp(new Date())}\n`;
            attendanceData += `Total Logins: ${attendanceRecords.length}\n\n`;
            attendanceData += '--- Login Records ---\n\n';
            
            attendanceRecords.forEach((record, index) => {
                attendanceData += `${index + 1}. Username: ${record.username}\n`;
                attendanceData += `   Section/Occupation: ${record.sectionOccupation || 'N/A'}\n`;
                attendanceData += `   Timestamp: ${record.timestamp}\n\n`;
            });
            
            const blob = new Blob([attendanceData], { type: 'text/plain' });
            const link = document.createElement('a');
            link.href = window.URL.createObjectURL(blob);
            link.download = 'attendance_summary.txt';
            link.click();
        }

        function downloadCSV() {
            if (!attendanceRecords || attendanceRecords.length === 0) {
                alert('No attendance records to export.');
                return;
            }

            const escape = (s = '') => `"${String(s).replace(/"/g, '""')}"`;
            const header = ['username', 'sectionOccupation', 'timestamp'];
            const rows = attendanceRecords.map(r => [r.username || '', r.sectionOccupation || '', r.timestamp || ''].map(escape).join(','));
            // Add BOM so Excel recognizes UTF-8 correctly
            const csvContent = '\uFEFF' + [header.join(','), ...rows].join('\n');
            const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
            const link = document.createElement('a');
            link.href = URL.createObjectURL(blob);
            link.download = 'attendance_summary.csv';
            document.body.appendChild(link);
            link.click();
            link.remove();
        }

        function logout() {
            document.getElementById('loginForm').style.display = 'block';
            document.getElementById('welcomeSection').style.display = 'none';
            document.getElementById('login').reset();
            const cs = document.getElementById('classificationSummary');
            if (cs) cs.style.display = 'none';
        }

        function generateClassificationSummary() {
            const container = document.getElementById('classificationSummary');
            if (!container) return;

            if (attendanceRecords.length === 0) {
                container.innerHTML = '<em>No records to summarize.</em>';
                container.style.display = 'block';
                return;
            }

            const bySection = {};
            const byInitial = {};

            attendanceRecords.forEach(r => {
                const sec = (r.sectionOccupation || 'Unspecified').trim() || 'Unspecified';
                bySection[sec] = (bySection[sec] || 0) + 1;

                const initial = (r.username && r.username[0]) ? r.username[0].toUpperCase() : '#';
                byInitial[initial] = (byInitial[initial] || 0) + 1;
            });

            let html = '<strong>Classification Summary</strong><br><br>';
            html += '<em>By Section/Occupation:</em><br>';
            Object.keys(bySection).sort().forEach(k => {
                html += `${k}: ${bySection[k]}<br>`;
            });

            html += '<br><em>By Name Initial:</em><br>';
            Object.keys(byInitial).sort().forEach(k => {
                html += `${k}: ${byInitial[k]}<br>`;
            });

            container.innerHTML = html;
            container.style.display = 'block';
        }

        const showBtn = document.getElementById('showClassificationBtn');
        if (showBtn) {
            showBtn.addEventListener('click', (e) => {
                e.preventDefault();
                generateClassificationSummary();
                const c = document.getElementById('classificationSummary');
                if (c) c.scrollIntoView({ behavior: 'smooth' });
            });
        }

        const style = document.createElement('style');
        style.textContent = `
            @keyframes shake {
                0%, 100% { transform: translateX(0); }
                25% { transform: translateX(-10px); }
                75% { transform: translateX(10px); }
            }
        `;
        document.head.appendChild(style);