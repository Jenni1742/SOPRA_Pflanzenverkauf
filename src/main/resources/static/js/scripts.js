function toggleWishlist(button, plantId) {
    const heartIcon = button.querySelector('.heart-icon');

    if (heartIcon.classList.contains('liked')) {
        heartIcon.classList.remove('liked');
        removeFromWishlist(plantId);
    } else {
        heartIcon.classList.add('liked');
        addToWishlist(plantId);
    }
}

function addToWishlist(plantId) {
    let wishlist = JSON.parse(localStorage.getItem('wishlist')) || [];
    if (!wishlist.includes(plantId)) {
        wishlist.push(plantId);
    }
    localStorage.setItem('wishlist', JSON.stringify(wishlist));
}

function removeFromWishlist(plantId) {
    let wishlist = JSON.parse(localStorage.getItem('wishlist')) || [];
    wishlist = wishlist.filter(id => id !== plantId);
    localStorage.setItem('wishlist', JSON.stringify(wishlist));
}

// Optional: Beim Laden der Seite überprüfen, ob die Artikel in der Wunschliste sind und das Herz entsprechend markieren
document.addEventListener('DOMContentLoaded', () => {
    let wishlist = JSON.parse(localStorage.getItem('wishlist')) || [];
    wishlist.forEach(plantId => {
        const heartIcon = document.querySelector(`.heart-icon[data-id='${plantId}']`);
        if (heartIcon) {
            heartIcon.classList.add('liked');
        }
    });
});

