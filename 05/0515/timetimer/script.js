const clockBackground = document.getElementById('clockBackground');
const clock = document.getElementById('clock');
const secondHand = document.getElementById('secondHand');
const rot = document.getElementById('rot');
const draggable = document.getElementById('draggable');
const hiddenArea = document.getElementById('hiddenArea');

let isDragging = false;
let isTransitioning = false;
let isTicking = false;
let originX = 0;
let originY = 0;
let initialRotation;
var angle = 0;
let min = 0;
let sec = 0;
var timeCount = 1;

function changeTimeText(min, sec) {
  if(min < 10) {min = '0' + min};
  if(sec < 10) {sec = '0' + sec};
  rot.textContent = min + ":" + sec;
}

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
secondHand.addEventListener('mousedown', (event) => {
    isDragging = true;
    changeH1ToTime();
    const rect = secondHand.getBoundingClientRect();
    originX = event.clientX;
    originY = event.clientY;
  });
  
  document.addEventListener('mousemove', (event) => {
    event.preventDefault();
    if (isDragging) { 
      const dx = event.clientX - originX;
      const dy = event.clientY - originY;
      angle = Math.atan2(dy, dx) * (180 / Math.PI);
      if(angle < 0)
      angle += 360;
      if(isTransitioning) {
        min = Math.floor(`${60 * (angle / 360)}`);
        sec = Math.floor(`${(3600 * (angle / 360)) % 60}`); 
        changeTimeText(min, sec);
      }
      secondHand.style.transform = `rotate(${angle + 180}deg)`;
      
      const handLength = 500;
      const hiddenAreaLength = (degree / 360) * handLength;
      hiddenArea.style.display = 'block';
      hiddenArea.style.width = `${hiddenAreaLength}px`;
      hiddenArea.style.height = `${hiddenAreaLength}px`;
    }
  });
  
  document.addEventListener('mouseup', (event) => {
    isDragging = false;
    isTransitioning = false;
    isTicking = true;
    if(timeCount === 1)
      updateTime(isTicking);
    setTimerDraggable();
    console.log('handOff'); 
  });

  // 시간 감소
function updateTime(isTicking) {
  if(isTicking){
    setInterval(() => {
      sec -= 1;
      if(sec == 0)
      {
        min -= 1;
        sec = 59;
      }
      changeTimeText(min, sec);
      // 1분에 6도, 1초에 (1/60*6도 -> 1/10도)
      initialRotation = angle + 180;
      secondHand.style.transform = `rotate(${initialRotation - timeCount / 10}deg)`;
      console.log(initialRotation - timeCount);
      timeCount++;
    }, 1000);
  }
}

  // secondHand.addEventListener('mouseup', (event) => {
  //   isDragging = false;
  //   isTransitioning = false;
  //   isTicking = true;
  //   updateTime();
  //   setTimerDraggable();
  //   console.log('handOff');
  // });

function changeH1ToTime() {
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

updateTime(isTicking);
drawDials();