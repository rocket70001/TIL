const clockBackground = document.getElementById('clockBackground');
const clock = document.getElementById('clock');
const secondHand = document.getElementById('secondHand');
const rot = document.getElementById('rot');
const draggable = document.getElementById('draggable');

function drawDials() {
    for(let i = 1; i <= 60; i++) {
        const newLine = document.createElement('div');
        newLine.classList.add('line');
        newLine.style.transform = `rotate(${6 * i}deg)`;
        if(i % 5) {
            newLine.classList.add('sec');
        } else {
            newLine.classList.add('min');
        }
       clockBackground.appendChild(newLine);
    }
}

// 마우스로 초침 이동 이벤트 발생시키기
let isDragging = false;
let isTransitioning = false;
let isTicking = false;
let originX = 0;
let originY = 0;
let originLeft = 0;
let originTop = 0;
let initialRotation = 0;
let angle = 0;

secondHand.addEventListener('mousedown', (event) => {
    isDragging = true;
    changeToTime();
    const rect = secondHand.getBoundingClientRect();
    originX = event.clientX;
    originY = event.clientY;
    initialRotation = getRotationValue(secondHand);
  
    // 초침의 위치를 마우스 클릭 위치로 이동
    const dx = originX - rect.left - rect.width / 2;
    const dy = originY - rect.top - rect.height / 2;
    const angle = Math.atan2(dy, dx) * (180 / Math.PI);
    secondHand.style.transform = `rotate(${initialRotation + angle}deg)`;
  });
  
  document.addEventListener('mousemove', (event) => {
    if (isDragging) { 
      const dx = event.clientX - originX;
      const dy = event.clientY - originY;
      const angle = Math.atan2(dy, dx) * (180 / Math.PI);
      const newRotation = initialRotation + angle;
      if(isTransitioning)
        rot.textContent = `${Math.floor(3600 / newRotation)}:${Math.floor(3600 / newRotation)}`;
  
      // 초침의 회전 각도 적용
      secondHand.style.transform = `rotate(${newRotation}deg)`;
    }
  });
  
  document.addEventListener('mouseup', (event) => {
    isDragging = false;
    isTransitioning = false;
    isTicking = true;
    setTimerDraggable();
  });
  
  function getRotationValue(element) {
    const transformStyle = getComputedStyle(element).getPropertyValue('transform');
    const matrix = transformStyle.match(/^matrix\((.+)\)$/);
    if (matrix) {
      const values = matrix[1].split(', ');
      if (values.length === 6) {
        const a = parseFloat(values[0]);
        const b = parseFloat(values[1]);
        const radians = Math.atan2(b, a);
        console.log("동작중");
        return radians * (180 / Math.PI);
      }
    }
    return 0;
  }

function changeToTime() {
    rot.classList.add('text-transition');
    setTimeout(() => {
        rot.classList.remove('text-transition');
        rot.classList.add('text-time');
        isTransitioning = true;
      }, 700);
}

// 화살표 애니메이션 추가
function setTimerDraggable() {
    if(isTicking) {
        draggable.classList.add('text-transition');
        console.log("isTicking 동작중");
        console.log(draggable);
        setTimeout(() => {
            draggable.classList.remove('text-transition');
            draggable.style.visibility = 'visible';
        }, 800);
        setTimeout(() => {
            draggable.classList.add('text-transition');
        }, 6000);
}
}

drawDials();