document.addEventListener('DOMContentLoaded', () => {
    const seed = document.getElementById('seed');
    const water = document.getElementById('water');
    const fertilizer = document.getElementById('fertilizer');
    const plant = document.getElementById('plant');
    const sprout = document.getElementById('sprout');
    const flower = document.getElementById('flower');
    const scoreDisplay = document.getElementById('score');
    let score = 0;
    let plantStage = 0;

    const stages = ['seed', 'sprout', 'flower'];

    function allowDrop(event) {
        event.preventDefault();
    }

    function drag(event) {
        event.dataTransfer.setData('text', event.target.id);
    }

    function drop(event) {
        event.preventDefault();
        const data = event.dataTransfer.getData('text');
        const element = document.getElementById(data);

        if (element.id === 'seed' && plantStage === 0) {
            plantStage++;
            updatePlantStage();
        } else if (element.id === 'water' && plantStage === 1) {
            plantStage++;
            updatePlantStage();
            score += 10;
            scoreDisplay.textContent = score;
        } else if (element.id === 'fertilizer' && plantStage === 2) {
            plantStage++;
            updatePlantStage();
            score += 20;
            scoreDisplay.textContent = score;
        }
    }

    function updatePlantStage() {
        stages.forEach(stage => {
            document.getElementById(stage).classList.add('hidden');
        });
        if (plantStage < stages.length) {
            document.getElementById(stages[plantStage]).classList.remove('hidden');
        }
    }

    seed.addEventListener('dragstart', drag);
    water.addEventListener('dragstart', drag);
    fertilizer.addEventListener('dragstart', drag);

    plant.addEventListener('dragover', allowDrop);
    plant.addEventListener('drop', drop);
});
