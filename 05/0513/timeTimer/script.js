const clock_background = document.getElementById('clock_background');

// clock 히든으로 해놨다가 시침 움직이면 퍼센티지 노출, 시간 계산, 시간 타이핑 입력란

// clock_background에 div 할당해서 분침, 초침 그리기

function drawDials() {
    for(let i = 0; i < 60; i++) {
        const newLine = document.createElement('div');
        newLine.classList.add('line');
        newLine.style.transform = `rotate(${6 * i}deg)`;
        if(i % 5) {
            newLine.classList.add('sec');
        } else {
            newLine.classList.add('min');
        }
        clock_background.appendChild(newLine);
    }
}

drawDials();