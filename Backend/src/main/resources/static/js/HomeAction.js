document.addEventListener("DOMContentLoaded", () => {
    const svg = document.getElementById("floating-svg");
    const logoImg = document.getElementById("logo-img");
    const themeBtn = document.getElementById('themeToggleBtn');

    const totalPaths = 12;
    const fragment = document.createDocumentFragment();

    const updateSize = () => {
        svg.setAttribute("viewBox", `0 0 ${window.innerWidth} ${window.innerHeight}`);
    };
    updateSize();
    window.addEventListener("resize", updateSize);

    for (let i = 0; i < totalPaths; i++) {
        const path = document.createElementNS("http://www.w3.org/2000/svg", "path");
        const startY = (window.innerHeight / totalPaths) * i;
        const d = `M-100 ${startY} C${window.innerWidth * 0.4} ${startY - 150} ${window.innerWidth * 0.6} ${startY + 150} ${window.innerWidth + 100} ${startY}`;
        path.setAttribute("d", d);
        path.setAttribute("stroke", "#3b82f6");
        path.setAttribute("stroke-width", 1.2 + (i * 0.1));
        path.setAttribute("fill", "none");
        path.style.opacity = "0.3";
        fragment.appendChild(path);
        path.style.strokeDasharray = "60 140";
        path.animate([{ strokeDashoffset: 1000 }, { strokeDashoffset: 0 }], {
            duration: 18000 + (Math.random() * 7000),
            iterations: Infinity,
            easing: 'linear'
        });
    }
    svg.appendChild(fragment);

    const updateLogo = (theme) => {
        logoImg.src = theme === 'dark' ? "/imagenes/LogoOscuro.png" : "/imagenes/Logo.png";
    };

    const savedTheme = localStorage.getItem('metro-theme') || 'light';
    document.body.setAttribute('data-theme', savedTheme);
    updateLogo(savedTheme);

    themeBtn.addEventListener('click', () => {
        const currentTheme = document.body.getAttribute('data-theme');
        const newTheme = currentTheme === 'light' ? 'dark' : 'light';
        document.body.setAttribute('data-theme', newTheme);
        localStorage.setItem('metro-theme', newTheme);
        updateLogo(newTheme);
    });
});