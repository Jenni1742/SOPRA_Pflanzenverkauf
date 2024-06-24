document.addEventListener('DOMContentLoaded', () => {
    const weedsContainer = document.getElementById('weeds-container');
    const basket = document.getElementById('basket');
    const weedCountElement = document.getElementById('weed-count');
    const plantCoinCountElement = document.getElementById('plantcoin-count');
    let weedCount = 0;
    let plantCoinCount = localStorage.getItem('plantcoins') || 0;
    plantCoinCountElement.textContent = plantCoinCount;

    let weedIdCounter = 0;

    function addRandomWeed() {
        const weed = document.createElement('img');
        weed.src = 'images/unkraut.png'; // Pfad zum Unkrautbild
        weed.className = 'weed';
        weed.id = `weed-${weedIdCounter++}`;
        weed.style.top = `${Math.random() * 350}px`;
        weed.style.left = `${Math.random() * (document.getElementById('garden').offsetWidth - 50)}px`;
        weedsContainer.appendChild(weed);

        // Drag-and-Drop-Ereignisse
        weed.draggable = true;
        weed.addEventListener('dragstart', (e) => {
            console.log('Drag start');
            e.dataTransfer.setData('text/plain', e.target.id);
            e.dataTransfer.effectAllowed = 'move';
        });
    }

    for (let i = 0; i < 5; i++) {
        addRandomWeed();
    }

    setInterval(addRandomWeed, 5000);

    basket.addEventListener('dragover', (e) => {
        e.preventDefault();
        e.dataTransfer.dropEffect = 'move';
        console.log('Drag over basket');
    });

    basket.addEventListener('drop', (e) => {
        e.preventDefault();
        console.log('Drop event');
        const weedId = e.dataTransfer.getData('text/plain');
        const weed = document.getElementById(weedId);
        if (weed) {
            weed.remove();
            weedCount++;
            weedCountElement.textContent = weedCount;
            if (weedCount % 10 === 0) {
                plantCoinCount++;
                plantCoinCountElement.textContent = plantCoinCount;
                localStorage.setItem('plantcoins', plantCoinCount);
            }
        }
    });
});