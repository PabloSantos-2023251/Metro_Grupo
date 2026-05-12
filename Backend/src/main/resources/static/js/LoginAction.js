const pupils = [
    { l: document.getElementById('pupilL1'), r: document.getElementById('pupilR1'), le: document.getElementById('eyeL1'), re: document.getElementById('eyeR1'), max: 5 },
    { l: document.getElementById('pupilL2'), r: document.getElementById('pupilR2'), le: document.getElementById('eyeL2'), re: document.getElementById('eyeR2'), max: 4 },
];

let passwordVisible = false;
let passwordHasValue = false;
const passwordInput = document.getElementById('password');

const lookAway = () => {
    pupils.forEach(p => {
        if (!p.l || !p.r) return;
        p.l.style.transform = `translate(calc(-50% - 12px), calc(-50% - 2px))`;
        p.r.style.transform = `translate(calc(-50% - 12px), calc(-50% - 2px))`;
    });
};

document.addEventListener('mousemove', (e) => {
    if (passwordVisible) {
        lookAway();
        return;
    }

    pupils.forEach(p => {
        const move = (el, eye, mX, mY, max) => {
            if (!el || !eye) return;
            const r = eye.getBoundingClientRect();
            const dx = mX - (r.left + r.width/2), dy = mY - (r.top + r.height/2);
            const d = Math.min(Math.hypot(dx, dy), max), a = Math.atan2(dy, dx);
            el.style.transform = `translate(calc(-50% + ${Math.cos(a)*d}px), calc(-50% + ${Math.sin(a)*d}px))`;
        };
        move(p.l, p.le, e.clientX, e.clientY, p.max);
        move(p.r, p.re, e.clientX, e.clientY, p.max);
    });
});

document.getElementById('togglePw').addEventListener('click', () => {
    passwordVisible = !passwordVisible;
    passwordInput.type = passwordVisible ? 'text' : 'password';

    const icon = document.getElementById('eyeIcon');
    icon.innerHTML = passwordVisible
        ? `<path d="M17.94 17.94A10.07 10.07 0 0112 20c-7 0-11-8-11-8a18.45 18.45 0 015.06-5.94M9.9 4.24A9.12 9.12 0 0112 4c7 0 11 8 11 8a18.5 18.5 0 01-2.16 3.19m-6.72-1.07a3 3 0 11-4.24-4.24"/><line x1="1" y1="1" x2="23" y2="23"/>`
        : `<path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"/><circle cx="12" cy="12" r="3"/>`;

    if (passwordVisible) {
        lookAway();
    }
});

function autoBlink() {
    document.querySelectorAll('.eye-socket').forEach(e => {
        e.classList.add('blink-anim');
        setTimeout(() => e.classList.remove('blink-anim'), 150);
    });
    setTimeout(autoBlink, 3000 + Math.random() * 4000);
}

window.addEventListener('load', () => {
    document.getElementById('trainNavy').classList.add('arrive');
    setTimeout(() => document.getElementById('trainTeal').classList.add('arrive'), 300);
    autoBlink();
});