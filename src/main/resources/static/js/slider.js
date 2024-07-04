const slider1 = document.getElementById('slider-1');
const slider2 = document.getElementById('slider-2');
const track = document.getElementById('track');
const value1 = document.getElementById('value-1');
const value2 = document.getElementById('value-2');
const maxVal = slider1.max;

function updateTrack() {
    const percent1 = (slider1.value / maxVal) * 100;
    const percent2 = (slider2.value / maxVal) * 100;
    track.style.left = percent1 + '%';
    track.style.width = (percent2 - percent1) + '%';
    value1.textContent = slider1.value + ' EUR';
    value2.textContent = slider2.value + ' EUR';
}

slider1.addEventListener('input', () => {
    if (parseInt(slider1.value) > parseInt(slider2.value)) {
        slider1.value = slider2.value;
    }
    updateTrack();
});

slider2.addEventListener('input', () => {
    if (parseInt(slider2.value) < parseInt(slider1.value)) {
        slider2.value = slider1.value;
    }
    updateTrack();
});

updateTrack();

/* Neuer Code für den Größen-Slider */
const sliderSize1 = document.getElementById('slider-size-1');
const sliderSize2 = document.getElementById('slider-size-2');
const trackSize = document.getElementById('track-size');
const valueSize1 = document.getElementById('value-size-1');
const valueSize2 = document.getElementById('value-size-2');
const maxSizeVal = sliderSize1.max;

function updateSizeTrack() {
    const percent1 = (sliderSize1.value / maxSizeVal) * 100;
    const percent2 = (sliderSize2.value / maxSizeVal) * 100;
    trackSize.style.left = percent1 + '%';
    trackSize.style.width = (percent2 - percent1) + '%';
    valueSize1.textContent = sliderSize1.value + ' cm';
    valueSize2.textContent = sliderSize2.value + ' cm';
}

sliderSize1.addEventListener('input', () => {
    if (parseInt(sliderSize1.value) > parseInt(sliderSize2.value)) {
        sliderSize1.value = sliderSize2.value;
    }
    updateSizeTrack();
});

sliderSize2.addEventListener('input', () => {
    if (parseInt(sliderSize2.value) < parseInt(sliderSize1.value)) {
        sliderSize2.value = sliderSize1.value;
    }
    updateSizeTrack();
});

updateSizeTrack();
