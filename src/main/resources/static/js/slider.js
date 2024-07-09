document.addEventListener('DOMContentLoaded', function() {
    // Preis-Schieberegler initialisieren
    const priceSliderMin = document.getElementById('slider-1');
    const priceSliderMax = document.getElementById('slider-2');
    const priceValueMin = document.getElementById('value-1');
    const priceValueMax = document.getElementById('value-2');

    // Funktion zur Aktualisierung der Preis-Schieberegler-Anzeige
    function updatePriceRange() {
        const minPrice = priceSliderMin.value;
        const maxPrice = priceSliderMax.value;

        priceValueMin.textContent = minPrice + ' EUR';
        //priceValueMax.textContent = (maxPrice < 100) ? (maxPrice + ' EUR') : '100 EUR und mehr';
        priceValueMax.textContent = (maxPrice == 100) ? '100 EUR und mehr' : maxPrice + ' EUR';

        // Update des Bereichs zwischen den Handles
            const rangeWidth = (maxPrice - minPrice) / (priceSliderMax.max - priceSliderMin.min) * 100;
            const rangeLeft = (minPrice - priceSliderMin.min) / (priceSliderMax.max - priceSliderMin.min) * 100;

            const rangeTrack = document.querySelector('.range-track-price');
            rangeTrack.style.width = rangeWidth + '%';
            rangeTrack.style.left = rangeLeft + '%';
    }

    // Eventlistener für Änderungen der Preis-Schieberegler
    priceSliderMin.addEventListener('input', function() {
        if (parseInt(priceSliderMin.value) > parseInt(priceSliderMax.value)) {
            priceSliderMin.value = priceSliderMax.value;
        }
        updatePriceRange();
    });

    priceSliderMax.addEventListener('input', function() {
        if (parseInt(priceSliderMax.value) < parseInt(priceSliderMin.value)) {
            priceSliderMax.value = priceSliderMin.value;
        }
        updatePriceRange();
    });

    // Größe-Schieberegler initialisieren
    const sizeSliderMin = document.getElementById('slider-size-1');
    const sizeSliderMax = document.getElementById('slider-size-2');
    const sizeValueMin = document.getElementById('value-size-1');
    const sizeValueMax = document.getElementById('value-size-2');

    // Funktion zur Aktualisierung der Größe-Schieberegler-Anzeige
    function updateSizeRange() {
        const minSize = sizeSliderMin.value;
        const maxSize = sizeSliderMax.value;

        sizeValueMin.textContent = minSize + ' cm';
        //sizeValueMax.textContent = (maxSize < 100) ? (maxSize + ' cm') : '100 cm und mehr';
        sizeValueMax.textContent = (maxSize == 100) ? '100 cm und mehr' : maxSize + ' cm';

        // Update des Bereichs zwischen den Handles
            const rangeWidth = (maxSize - minSize) / (sizeSliderMax.max - sizeSliderMin.min) * 100;
            const rangeLeft = (minSize - sizeSliderMin.min) / (sizeSliderMax.max - sizeSliderMin.min) * 100;

            const rangeTrack = document.querySelector('.range-track-size');
            rangeTrack.style.width = rangeWidth + '%';
            rangeTrack.style.left = rangeLeft + '%';
    }

    // Eventlistener für Änderungen der Größe-Schieberegler
    sizeSliderMin.addEventListener('input', function() {
        if (parseInt(sizeSliderMin.value) > parseInt(sizeSliderMax.value)) {
            sizeSliderMin.value = sizeSliderMax.value;
        }
        updateSizeRange();
    });

    sizeSliderMax.addEventListener('input', function() {
        if (parseInt(sizeSliderMax.value) < parseInt(sizeSliderMin.value)) {
            sizeSliderMax.value = sizeSliderMin.value;
        }
        updateSizeRange();
    });

    // Initialisierung der Schieberegler beim Laden der Seite
    updatePriceRange();
    updateSizeRange();
});
