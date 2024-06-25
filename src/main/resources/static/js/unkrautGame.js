document.addEventListener('DOMContentLoaded', () => {
    const weedsContainer = document.getElementById('weeds-container');
    const basket = document.getElementById('basket');
    const weedCountElement = document.getElementById('weed-count');
    const plantCoinCountElement = document.getElementById('plantcoin-count');
    let weedCount = 0;
    let plantCoinCount = parseInt(plantCoinCountElement.textContent, 10) || 0;

    let weedIdCounter = 0;

    function addRandomWeed() {
        const weed = document.createElement('img');
        weed.src = 'images/unkraut.png';
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

    setInterval(addRandomWeed, 4000);

    basket.addEventListener('dragover', (e) => {
        e.preventDefault();
        e.dataTransfer.dropEffect = 'move';
        console.log('Drag over basket');
    });

basket.addEventListener('drop', async (e) => {
    e.preventDefault();
    const weedId = e.dataTransfer.getData('text/plain');
    const weed = document.getElementById(weedId);
    if (weed) {
        weed.remove();
        weedCount++;
        weedCountElement.textContent = weedCount;
        if (weedCount % 2 === 0) {
            plantCoinCount++;
            plantCoinCountElement.textContent = plantCoinCount;

            updatePlantCoins(plantCoinCount);
        }
    }
});

function updatePlantCoins(newPlantCoinAmount) {

    document.getElementById('coinCountInput').innerText = newPlantCoinAmount;
    document.getElementById('coinCountInput').value = newPlantCoinAmount;

     console.log("document.getElementById('coinCountInput').innerText");
    console.log(document.getElementById('coinCountInput').innerText);


    // Fetch CSRF token from the cookie
    const csrfToken = getCookie('XSRF-TOKEN');

    // Make the AJAX request with CSRF token in headers
    fetch('/unkrautjagd/update', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-XSRF-TOKEN': csrfToken  // Ensure the token name matches your configuration
        },
        body: JSON.stringify({ plantCoin: newPlantCoinAmount })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        console.log('Updated plantCoin successfully:', data);
        // Handle successful response
    })
    .catch(error => {
        console.error('Failed to update plantCoin:', error);
        // Handle error
    });
}


// Helper function to get cookie value
function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}
});