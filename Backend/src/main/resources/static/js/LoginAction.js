const pupils = [
    { l: document.getElementById('pupilL1'), r: document.getElementById('pupilR1'), le: document.getElementById('eyeL1'), re: document.getElementById('eyeR1'), max: 5 },
    { l: document.getElementById('pupilL2'), r: document.getElementById('pupilR2'), le: document.getElementById('eyeL2'), re: document.getElementById('eyeR2'), max: 4 },
];

let passwordVisible = false;
let passwordHasValue = false;
const passwordInput = document.getElementById('password');
const trainNavy = document.getElementById('trainNavy');
const trainTeal = document.getElementById('trainTeal');

document.addEventListener('mousemove', (e) => {
    if (passwordHasValue && !passwordVisible) return;
    pupils.forEach(p => {
        const move = (el, eye, mX, mY, max) => {
            const r = eye.getBoundingClientRect();
            const dx = mX - (r.left + r.width/2), dy = mY - (r.top + r.height/2);
            const d = Math.min(Math.hypot(dx, dy), max), a = Math.atan2(dy, dx);
            el.style.transform = `translate(calc(-50% + ${Math.cos(a)*d}px), calc(-50% + ${Math.sin(a)*d}px))`;
        };
        move(p.l, p.le, e.clientX, e.clientY, p.max);
        move(p.r, p.re, e.clientX, e.clientY, p.max);
    });
});

passwordInput.addEventListener('input', () => {
    passwordHasValue = passwordInput.value.length > 0;
    if (passwordHasValue && !passwordVisible) {
        document.querySelectorAll('.eye-socket').forEach(e => e.classList.add('blink'));
        trainNavy.classList.add('shy'); trainTeal.classList.add('shy');
    } else {
        document.querySelectorAll('.eye-socket').forEach(e => e.classList.remove('blink'));
        trainNavy.classList.remove('shy'); trainTeal.classList.remove('shy');
    }
});

document.getElementById('togglePw').addEventListener('click', () => {
    passwordVisible = !passwordVisible;
    passwordInput.type = passwordVisible ? 'text' : 'password';
    const icon = document.getElementById('eyeIcon');
    icon.innerHTML = passwordVisible
    ? `<path d="M17.94 17.94A10.07 10.07 0 0112 20c-7 0-11-8-11-8a18.45 18.45 0 015.06-5.94M9.9 4.24A9.12 9.12 0 0112 4c7 0 11 8 11 8a18.5 18.5 0 01-2.16 3.19m-6.72-1.07a3 3 0 11-4.24-4.24"/><line x1="1" y1="1" x2="23" y2="23"/>`
    : `<path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"/><circle cx="12" cy="12" r="3"/>`;
    passwordInput.dispatchEvent(new Event('input'));
});

function autoBlink() {
    if (!trainNavy.classList.contains('shy')) {
        document.querySelectorAll('.eye-socket').forEach(e => {
            e.classList.add('blink-anim');
            setTimeout(() => e.classList.remove('blink-anim'), 150);
        });
    }
    setTimeout(autoBlink, 3000 + Math.random() * 4000);
}

function openModal() { document.getElementById('boletoModal').style.display = 'flex'; }
function closeModal() { document.getElementById('boletoModal').style.display = 'none'; }

window.addEventListener('load', () => {
    if(trainNavy) trainNavy.classList.add('arrive');
    setTimeout(() => { if(trainTeal) trainTeal.classList.add('arrive'); }, 300);
    autoBlink();
});

window.onclick = function(event) {
    const modal = document.getElementById('boletoModal');
    if (event.target == modal) { closeModal(); }
};