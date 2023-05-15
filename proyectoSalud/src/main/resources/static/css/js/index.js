/*carousel fotos*/


function moveCarousel() {
    var carouselInner = document.querySelector('.carousel-inner');
    carouselInner.classList.add('move');
    setTimeout(function() {
      carouselInner.appendChild(carouselInner.firstElementChild);
      carouselInner.classList.remove('move');
    }, 12000);
  }
  
  setInterval(moveCarousel, 3000);

  $(document).ready(function() {
    $('.carousel').carousel({
      interval: 12000 // Tiempo de intervalo entre las imágenes (en milisegundos)
    });
  });
  

  