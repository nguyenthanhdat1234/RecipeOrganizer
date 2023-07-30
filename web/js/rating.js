/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


document.addEventListener("DOMContentLoaded", function() {
  if (localStorage.getItem("ratingValue")) {
    const ratingValue = localStorage.getItem("ratingValue");
    
    const starInput = document.querySelector(`input.star[value="${ratingValue}"]`);
    
    starInput.checked = true;
    
    localStorage.removeItem("ratingValue");
  }
  
  const starInputs = document.querySelectorAll("input.star");
  starInputs.forEach(function(starInput) {
    starInput.addEventListener("change", function() {
      const ratingValue = this.value;
      localStorage.setItem("ratingValue", ratingValue);
    });
  });
});