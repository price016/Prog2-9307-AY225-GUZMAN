let students = [];
let selectedRow = null;

// Initial CSV data that loads automatically
const initialCSVData = `StudentID,first_name,last_name,LAB WORK 1,LAB WORK 2,LAB WORK 3,PRELIM EXAM,ATTENDANCE GRADE
073900438,Osbourne,Wakenshaw,69,5,52,12,78
114924014,Albie,Gierardi,58,92,16,57,97
111901632,Eleen,Pentony,43,81,34,36,16
084000084,Arie,Okenden,31,5,14,39,99
272471551,Alica,Muckley,49,66,97,3,95
104900721,Jo,Burleton,98,94,33,13,29
111924392,Cam,Akram,44,84,17,16,24
292970744,Celine,Brosoli,3,15,71,83,45
107004352,Alan,Belfit,31,51,36,70,48
071108313,Jeanette,Gilvear,4,78,15,69,69
042204932,Ethelin,MacCathay,48,36,23,1,11
111914218,Kakalina,Finnick,69,5,65,10,8
074906059,Mayer,Lorenzetti,36,30,100,41,92
091000080,Selia,Rosenstengel,15,42,85,68,28
055002480,Dalia,Tadd,84,86,13,91,22
063101111,Darryl,Doogood,36,3,78,13,100
071908827,Brier,Wace,69,92,23,75,40
322285668,Bucky,Udall,97,63,19,46,28
103006406,Haslett,Beaford,41,32,85,60,61
104913048,Shelley,Spring,84,73,63,59,3
051403517,Marius,Southway,28,75,29,88,92
021301869,Katharina,Storch,6,61,6,49,56
063115178,Hester,Menendez,70,46,73,40,56
084202442,Shaylynn,Scorthorne,50,80,81,96,83
275079882,Madonna,Willatt,23,12,17,83,5
071001041,Bancroft,Padfield,50,100,58,13,14
261170740,Rici,Everard,51,15,48,99,41
113105478,Lishe,Dashkovich,9,23,48,63,95
267089712,Alexandrina,Abate,34,54,79,44,71
041002203,Jordon,Ribbens,41,42,24,60,21
021308176,Jennette,Andrassy,63,13,100,67,4
122239937,Hamid,Chapell,90,92,44,43,47
021109935,Cordy,Crosetto,16,10,99,32,57
111026041,Tiphanie,Gwin,34,45,88,87,27
072408708,Leanor,Izachik,95,35,88,9,75
221370030,Lissy,Tuffley,90,30,84,60,86
104900048,Myrta,Mathieson,88,80,16,6,48
111311413,Cynthea,Fowles,82,59,13,97,23
091408695,Zacherie,Branch,67,6,8,78,10
231372183,Britney,Blackesland,78,79,36,23,83
263190634,Theda,Menco,50,13,7,11,8
021606580,Carin,Schrader,77,32,25,56,53
074902341,Shawn,Moston,64,91,6,95,21
107006088,Virge,Sinnat,20,1,78,44,92
091807254,Alano,Jotcham,66,35,99,48,83
011601029,Pietra,Roy,35,34,75,39,98
122240010,Orren,Danihelka,51,36,17,59,32
091400046,Angie,Grindell,51,54,55,59,61
071001630,Vachel,Swancock,41,31,88,24,24
061020977,Zane,Bellie,88,92,92,52,46
065403150,Delphine,Sirette,73,35,53,48,67
081211300,Oliver,Pynner,47,76,76,56,87
011601074,Barbra,Antyukhin,72,66,76,53,42
091014898,Charmain,Elce,61,76,84,6,64
042100049,Herold,Klawi,3,56,92,27,76
091906359,Mariann,Mousdall,18,42,25,74,47
101114992,Horatius,Romera,91,52,29,11,56
031307604,Dall,Laboune,71,61,78,51,11
111308031,Teddie,Carlett,39,85,48,93,87
114919676,Kelley,Klimentyonok,22,17,83,61,81
322283835,Colene,Corgenvin,13,3,73,96,15
051402071,Diannne,Pashan,82,91,70,78,56
081904808,Staffard,Cullerne,46,37,15,39,50
074914407,Alwin,Hartzog,32,10,90,19,73
103110813,Johnny,Calbreath,3,48,19,4,16
241282632,Sophronia,Fere,65,58,71,49,37
063106734,Timothea,Lambird,77,74,78,85,52
064102290,Lauralee,Mc Caghan,37,76,99,97,12
075017947,Denny,Dani,59,80,5,4,50
072414297,Marilin,Lilloe,1,76,99,40,25
083908420,Hephzibah,Mizzi,33,44,69,72,34
075902340,Jourdan,Toulamain,44,84,89,80,24
114901147,Natassia,Daniele,4,68,27,66,45
061000256,Kellina,Newlands,13,34,20,57,89
051009296,Andria,Thurske,1,8,46,52,72
084205656,Shanie,Marczyk,64,2,33,62,45
041211298,Norine,Spinella,85,33,2,2,77
071922379,Rudiger,Cornbell,40,44,62,51,28
092101030,Reynold,Dumbelton,3,25,70,75,84
265270413,Fielding,Gouldstraw,57,41,85,17,23
051502434,Toni,Wong,36,19,93,31,74
072413557,Daisey,Shireff,85,56,79,35,47
231371799,Cristin,Albutt,79,37,46,79,26
122203248,Peterus,Ojeda,30,26,78,61,41
084205766,Nissie,Winterflood,76,2,81,36,18
075900465,Franciska,Meatyard,90,11,97,41,93
051502434,Tyler,Alekhov,89,69,32,34,8
114922430,Cordy,Byllam,42,88,50,77,100
221982156,Gabriel,Limrick,92,94,30,91,41
084201223,Nonie,McGaffey,35,12,84,33,43
071909211,Kittie,Alman,45,22,28,100,47
071900663,Gran,Smithies,68,35,59,9,84
067006432,Sammy,Gundry,48,24,42,4,61
081503490,Ozzy,Iskowitz,56,54,78,55,77
081307227,Charlie,Waldram,91,41,59,87,82
211272465,Benn,Adnams,1,29,23,89,33
065404913,Fidelia,Katt,35,35,61,66,36
082900380,Fidelia,Jahndel,24,15,93,1,43
061101100,Marietta,Bourgourd,74,83,74,18,7
075072157,Elberta,Argyle,73,61,24,52,39
082900911,Dru,Hendrickson,60,97,80,38,74
301271790,Clemmie,Annett,76,3,27,26,70
122243321,Even,Stebbings,21,73,94,40,60
112204286,Eduardo,Scholz,55,18,35,18,7
064108605,Ruggiero,Colrein,69,8,93,79,63
256072701,Lesli,Tolefree,45,58,51,30,76
091201818,Caitrin,Bogeys,52,26,19,69,90
063115437,Rodrick,Bisset,46,39,11,12,53
211384162,Ladonna,Bridgewood,51,11,17,34,12
122238938,Westleigh,Trevear,23,19,23,99,65
111101377,Allen,Petrovsky,62,50,89,42,51
051404118,Tabbitha,Havers,64,51,23,1,57
262285223,Chev,Helstrip,16,91,9,28,96
072413599,Garvey,Done,30,17,2,18,43
075000734,Kalila,Wondraschek,40,54,72,33,25
065002030,Eustace,Hourican,91,98,72,54,83
084307033,Frayda,Drewell,29,28,39,25,16
114912275,Corena,Seyler,42,46,19,6,69
071925538,Aurie,Campanelli,40,91,64,39,48
263190757,Juanita,Ruhben,49,66,94,39,34
082905181,Danica,Hillam,70,65,53,50,98
211170169,Carney,Bastick,82,58,33,29,21
111304608,Rori,Wragge,76,37,72,21,38
101918240,Rebbecca,Holywell,45,51,90,46,70
091300719,Carina,Manuello,97,98,27,81,53
053202305,Mandi,Meas,55,10,92,59,89
071923747,Audry,Warkup,80,77,55,61,89
071001630,Meryl,Mackett,17,84,26,62,39
053201186,Richart,Tolliday,59,3,98,69,16
071105002,Alejoa,Kinkead,59,58,76,64,6
065203499,Minna,Dunkley,78,55,50,46,20
102101496,Alyse,Gabits,92,50,22,43,55
062000019,Orsa,Learmouth,23,94,7,4,77
042201948,Renata,Dykas,26,100,22,11,34
062206415,Rogers,Ivanyushin,53,57,25,62,93
122243457,Shelton,Kunc,25,86,21,33,17
061204612,Arlen,Gobeau,25,23,20,68,76
113110780,Fidel,Rodgers,31,62,92,75,34
113122655,Peadar,Brompton,22,36,83,51,12
103101929,Noel,Caught,17,15,54,17,59
061209756,Mitchell,Spickett,95,26,60,57,16
082901457,Carney,Redsull,41,40,34,50,98
211970055,Nelia,Mattke,79,11,81,10,26
065002108,Marcellus,Discombe,23,22,51,76,67
323270300,Polly,Savine,19,30,11,9,64
067005679,Nobe,Brake,1,22,59,37,65
073915520,Nollie,Locke,40,6,95,3,24
067091719,Dino,Davenhall,83,79,9,90,98
063113015,Frants,Boughey,46,66,87,15,94
104900679,Frannie,Rigg,51,53,84,44,97
111916452,Lazaro,Kid,47,29,65,64,55
111906161,Berny,Caps,11,63,12,13,47
264279334,Gerhard,McNellis,71,8,31,98,78
101106379,Demetra,Cristofor,79,75,77,74,98
113110816,Timmie,Mitcham,83,77,16,84,40
064108757,Holmes,Dunwoody,88,30,82,11,25
107003418,Sully,Dawid,17,53,10,3,75
073903150,Myer,Mandre,93,40,31,56,17
211372352,Marco,Drysdale,81,77,1,29,2
103112329,Tabina,Cardinale,59,41,45,11,100
092901803,Adams,Baythorp,23,2,5,60,73
122105252,Jerrold,Bailess,90,80,80,10,88
284272654,Colman,Cavil,35,85,60,44,61
111900455,Caryl,Audsley,28,9,70,15,78
021207358,Cindee,Tomik,64,8,41,88,53
083002533,Isis,Dudley,44,74,14,9,43
261170740,Caldwell,Izzard,51,63,96,64,21
101102496,Olimpia,Maymand,71,25,53,50,51
064203021,Rhiamon,Glowach,54,18,72,93,54
021172661,Beale,Kordova,34,1,75,41,18
122041523,Bebe,Crippen,65,51,42,1,94
065301689,Kippy,Joselin,83,59,77,34,81
111924305,Derron,Mothersdale,95,6,44,79,23
074000162,Early,Brilon,62,39,21,60,64
322271779,Vitoria,Baxstar,31,55,2,69,14
114922430,Giulietta,Finnie,44,92,8,4,61
055000770,Cosmo,Harriot,84,69,35,32,5
063114632,Netta,Glazyer,39,13,94,18,89
101102836,Ruggiero,Spelsbury,57,66,9,37,100
103101424,Sunny,Vogeler,3,51,100,17,83
111321270,Gustie,Roelvink,51,96,47,30,20
073920845,Marilin,Caldow,9,40,32,76,63
031317380,Lindsay,Slisby,19,10,63,51,53
065301197,Lily,Jowitt,20,97,8,70,95
071909198,Lezlie,Trotton,23,19,67,89,36
043000122,Lyndsie,Flaune,29,35,73,98,23
211272504,Riane,Gilford,44,78,2,3,64
091401919,Tami,Khotler,27,49,2,52,48
064108757,Marrissa,Alywin,79,63,3,60,84
071902629,Gav,Maile,57,53,49,33,33
031301053,Ben,Stockport,25,84,6,69,31
113014077,Fran,Eyckelbeck,93,31,79,74,21
065303069,Ruperto,Asp,13,82,29,25,17
071917232,Shanon,Polly,86,73,35,10,79
086518723,Noak,Pickring,24,90,2,39,3
101100922,Blinni,Marsie,27,16,57,69,72
111306871,Demetri,Plackstone,74,6,66,20,74
052100929,Fidole,Gremane,95,90,31,16,4
091015224,Magdalene,Bushell,40,66,88,70,24
071114491,Matthus,Cecely,70,75,48,4,55
031000040,Isac,Itzhaiek,83,25,11,67,62
103912723,Sawyer,Kaman,47,93,1,42,3
281581047,Berti,Finch,64,70,16,79,65
064000185,Jeff,Brogiotti,18,87,55,72,7
091916093,Elissa,Peaden,98,92,78,57,15
041201703,Skipp,Leet,82,85,86,63,79
243073632,Otha,Lynskey,46,68,9,78,56
113001077,Staford,Darell,37,24,64,77,50
103106843,Tony,Cole,34,30,37,71,51
221472776,Kleon,Caskie,5,54,34,42,91
075901642,Mathilde,Lembrick,75,69,29,70,60
104000702,Lita,McCroft,19,9,13,53,28
226072375,Terrijo,Tersay,8,63,7,96,80
253173661,Janot,Wittleton,68,4,86,56,41
264271361,Dalis,Marrable,68,19,26,28,56
075907497,Elisabeth,Girsch,81,62,74,77,95
113104521,Abe,Bovingdon,86,100,51,57,73
062000080,Luca,Spir,71,30,74,44,7
322271096,Colby,Greatex,73,10,74,78,52
071111986,Bobine,Parres,12,98,49,88,38
103000800,Aarika,Sporrij,92,67,67,76,82
091404369,Ajay,Marnes,26,19,98,80,81
051404260,Duane,Novotni,24,50,45,82,88
081501489,Glennie,Colquite,61,6,41,10,46
063292499,Nicol,Dicks,16,48,85,36,82
051409139,Marsha,McOmish,35,30,42,80,79
061208126,Harmonie,Blose,20,10,39,94,48
221471971,Mari,Stollman,65,45,100,36,72
274070439,Levy,Roughey,68,5,63,91,50
026015079,Lenka,Crake,82,97,3,69,53
122105760,Shelagh,Liebrecht,24,9,54,89,81
084202219,Lilly,Stading,36,80,72,92,1
101000789,Lise,Grabbam,6,65,38,14,21
103012869,Laina,Olenchikov,73,100,60,4,65
082907888,Gerek,Marages,74,51,94,4,4
063114700,Cliff,Starcks,95,17,24,39,20
063115738,Darla,Pasek,57,2,59,24,29
051404118,Christopher,Raynard,8,85,83,98,59
063114946,Georgina,Eisenberg,93,32,84,20,26
072406771,Angelica,Cresser,62,38,42,57,50
053200446,Constancia,Messum,68,49,68,48,77
111104853,Laney,Amphlett,33,31,16,30,20
125102278,Andras,Szymaniak,79,21,17,16,95
072402788,Pen,Gillino,12,44,50,20,82
091800329,Felipa,Newlan,17,48,33,62,72
021214875,Ulrich,Perri,35,30,30,38,28
123206406,Eduino,Hawkswell,64,81,91,3,26
122237751,Belva,Braemer,60,49,81,84,93
082904483,Cad,Quest,48,4,37,36,39
084303545,Abagail,Johnys,51,30,52,94,99
062206732,Esmaria,Ashley,69,9,29,70,31
061119477,Antonia,Clyne,71,14,39,27,14
084202235,Fidel,Jurczak,40,21,20,49,59
061019506,Jennee,Prewer,45,36,50,75,13
041002711,Edwin,Flasby,80,69,42,43,22
081000032,Rafaelita,Deeley,39,7,84,96,30
084203043,Gayler,Link,27,32,24,51,78
122243648,Mylo,Janeczek,50,8,68,82,8
053100355,Roseline,Mainz,67,72,69,63,39
061103920,Mallorie,Byars,51,91,46,75,73
075010193,Emmet,Hannaford,85,82,100,26,6
053102191,Fenelia,Gillson,13,80,30,32,7
281073555,Patric,Spawell,99,58,8,40,34
125105550,Elberta,Chafer,70,59,66,13,83
244172082,Van,Venus,3,97,3,99,49
251472636,Chet,De Zamudio,98,24,27,1,13
121142517,Kaylil,Bleacher,30,95,39,33,12
211870977,Dixie,Duerden,10,44,76,86,19
091308795,Lane,Erlam,56,19,61,83,98
253174851,Simona,Shapiro,1,77,89,18,83
111915770,Crissie,Kirsopp,3,62,27,48,90
071925169,Sonnie,Farquarson,51,45,70,90,76
104901610,Babita,Riddel,82,61,16,5,97
081914775,Mavis,Radki,97,75,41,62,52
031306029,Blair,Mosey,99,23,79,80,63`;

// Load data from localStorage on page load
window.onload = function() {
    loadData();
    
    // If no data in localStorage, load the initial CSV data
    if (students.length === 0) {
        loadInitialData();
    }
    
    renderTable();
};

function loadInitialData() {
    const lines = initialCSVData.split('\n');
    
    // Skip header line (first line)
    for (let i = 1; i < lines.length; i++) {
        const line = lines[i].trim();
        if (line) {
            const parts = line.split(',');
            if (parts.length >= 8) {
                const student = {
                    id: parts[0].trim(),
                    firstName: parts[1].trim(),
                    lastName: parts[2].trim(),
                    lab1: parts[3].trim(),
                    lab2: parts[4].trim(),
                    lab3: parts[5].trim(),
                    prelim: parts[6].trim(),
                    attendance: parts[7].trim()
                };
                students.push(student);
            }
        }
    }
    
    // Save to localStorage
    saveData();
}

function addStudent() {
    const studentId = document.getElementById('studentId').value.trim();
    const firstName = document.getElementById('firstName').value.trim();
    const lastName = document.getElementById('lastName').value.trim();
    const lab1 = document.getElementById('lab1').value.trim();
    const lab2 = document.getElementById('lab2').value.trim();
    const lab3 = document.getElementById('lab3').value.trim();
    const prelim = document.getElementById('prelim').value.trim();
    const attendance = document.getElementById('attendance').value.trim();

    if (!studentId || !firstName || !lastName || !lab1 || !lab2 || !lab3 || !prelim || !attendance) {
        showModal('Validation Error', 'Please fill all fields.', false);
        return;
    }

    const student = {
        id: studentId,
        firstName: firstName,
        lastName: lastName,
        lab1: lab1,
        lab2: lab2,
        lab3: lab3,
        prelim: prelim,
        attendance: attendance
    };

    students.push(student);
    saveData();
    renderTable();
    clearFields();
    showModal('Success', 'Student record added successfully!', false);
}

function deleteStudent() {
    if (selectedRow === null) {
        showModal('No Selection', 'Please select a row to delete.', false);
        return;
    }

    showModal('Confirm Delete', 'Are you sure you want to delete this record?', true, function() {
        students.splice(selectedRow, 1);
        selectedRow = null;
        saveData();
        renderTable();
        showModal('Success', 'Record deleted successfully!', false);
    });
}

function saveData() {
    localStorage.setItem('studentRecords', JSON.stringify(students));
}

function loadData() {
    const data = localStorage.getItem('studentRecords');
    if (data) {
        students = JSON.parse(data);
    }
}

function exportData() {
    let csv = 'StudentID,first_name,last_name,LAB WORK 1,LAB WORK 2,LAB WORK 3,PRELIM EXAM,ATTENDANCE GRADE\n';
    
    students.forEach(student => {
        csv += `${student.id},${student.firstName},${student.lastName},${student.lab1},${student.lab2},${student.lab3},${student.prelim},${student.attendance}\n`;
    });

    const blob = new Blob([csv], { type: 'text/csv' });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = 'class_records_export.csv';
    a.click();
    window.URL.revokeObjectURL(url);

    showModal('Success', 'File exported successfully!', false);
}

function importCSV(event) {
    const file = event.target.files[0];
    
    if (!file) {
        return;
    }

    if (!file.name.endsWith('.csv')) {
        showModal('Invalid File', 'Please select a valid CSV file.', false);
        return;
    }

    const reader = new FileReader();
    
    reader.onload = function(e) {
        try {
            const text = e.target.result;
            const lines = text.split('\n');
            
            // Ask if user wants to replace or append
            showModal(
                'Import Options', 
                'Do you want to replace existing records or append to them?', 
                true, 
                function() {
                    // Replace - clear existing data
                    students = [];
                    processCSVLines(lines);
                },
                function() {
                    // Append - keep existing data
                    processCSVLines(lines);
                }
            );
        } catch (error) {
            showModal('Import Error', 'Error reading CSV file. Please check the file format.', false);
        }
    };
    
    reader.onerror = function() {
        showModal('Import Error', 'Failed to read the file.', false);
    };
    
    reader.readAsText(file);
    
    // Reset the file input so the same file can be imported again
    event.target.value = '';
}

function processCSVLines(lines) {
    let importedCount = 0;
    
    // Skip header line (first line)
    for (let i = 1; i < lines.length; i++) {
        const line = lines[i].trim();
        if (line) {
            const parts = line.split(',');
            if (parts.length >= 8) {
                const student = {
                    id: parts[0].trim(),
                    firstName: parts[1].trim(),
                    lastName: parts[2].trim(),
                    lab1: parts[3].trim(),
                    lab2: parts[4].trim(),
                    lab3: parts[5].trim(),
                    prelim: parts[6].trim(),
                    attendance: parts[7].trim()
                };
                students.push(student);
                importedCount++;
            }
        }
    }
    
    saveData();
    renderTable();
    showModal('Success', `Successfully imported ${importedCount} student record(s)!`, false);
}

function renderTable(filteredStudents = null) {
    const tableBody = document.getElementById('tableBody');
    tableBody.innerHTML = '';
    
    const dataToRender = filteredStudents || students;

    dataToRender.forEach((student, index) => {
        const row = tableBody.insertRow();
        row.onclick = function() {
            selectRow(index, row);
        };

        row.insertCell(0).textContent = student.id;
        row.insertCell(1).textContent = student.firstName;
        row.insertCell(2).textContent = student.lastName;
        row.insertCell(3).textContent = student.lab1;
        row.insertCell(4).textContent = student.lab2;
        row.insertCell(5).textContent = student.lab3;
        row.insertCell(6).textContent = student.prelim;
        row.insertCell(7).textContent = student.attendance;
    });
}

function selectRow(index, rowElement) {
    // Remove previous selection
    const rows = document.querySelectorAll('#tableBody tr');
    rows.forEach(row => row.classList.remove('selected'));

    // Add selection to clicked row
    rowElement.classList.add('selected');
    selectedRow = index;
}

function clearFields() {
    document.getElementById('studentId').value = '';
    document.getElementById('firstName').value = '';
    document.getElementById('lastName').value = '';
    document.getElementById('lab1').value = '';
    document.getElementById('lab2').value = '';
    document.getElementById('lab3').value = '';
    document.getElementById('prelim').value = '';
    document.getElementById('attendance').value = '';
}

function performSearch() {
    const searchText = document.getElementById('searchInput').value.toLowerCase().trim();

    if (!searchText) {
        renderTable();
        return;
    }

    const filtered = students.filter(student => {
        return Object.values(student).some(value => 
            value.toString().toLowerCase().includes(searchText)
        );
    });

    renderTable(filtered);
}

function clearSearch() {
    document.getElementById('searchInput').value = '';
    renderTable();
}

function showModal(title, message, isConfirm, onConfirm, onCancel) {
    const modal = document.getElementById('modal');
    const modalTitle = document.getElementById('modalTitle');
    const modalMessage = document.getElementById('modalMessage');
    const modalButtons = document.getElementById('modalButtons');

    modalTitle.textContent = title;
    modalMessage.textContent = message;

    if (isConfirm) {
        // Check if this is an import options dialog (has two callbacks)
        if (onCancel) {
            modalButtons.innerHTML = `
                <button class="btn btn-danger" onclick="confirmAction()">Replace</button>
                <button class="btn btn-success" onclick="cancelAction()">Append</button>
            `;
            window.confirmCallback = onConfirm;
            window.cancelCallback = onCancel;
        } else {
            // Regular confirmation dialog
            modalButtons.innerHTML = `
                <button class="btn btn-danger" onclick="confirmAction()">Yes</button>
                <button class="btn btn-primary" onclick="closeModal()">No</button>
            `;
            window.confirmCallback = onConfirm;
        }
    } else {
        modalButtons.innerHTML = `
            <button class="btn btn-primary" onclick="closeModal()">OK</button>
        `;
    }

    modal.classList.add('show');
}

function confirmAction() {
    if (window.confirmCallback) {
        window.confirmCallback();
        window.confirmCallback = null;
    }
    closeModal();
}

function cancelAction() {
    if (window.cancelCallback) {
        window.cancelCallback();
        window.cancelCallback = null;
    }
    closeModal();
}

function closeModal() {
    const modal = document.getElementById('modal');
    modal.classList.remove('show');
}