const laptopList = document.getElementsByClassName("elementLaptop");
const laptopArray = [...laptopList];

const laptopListFirstFour = laptopArray.slice(0, 8);

const laptopListJoin = laptopListFirstFour.join(" ");

document.getElementsByClassName("laptopFourElement").innerHTML = laptopListJoin;


const screenList = document.getElementsByClassName("elementScreen");
const screenArray = [...screenList];

const screenListFirstFour = screenArray.slice(0, 8);

const screenListJoin = screenListFirstFour.join(" ");

document.getElementsByClassName("screenFourElement").innerHTML = screenListJoin;


const mouseList = document.getElementsByClassName("elementMouse");
const mouseArray = [...mouseList];

const mouseListFirstFour = mouseArray.slice(0, 8);

const mouseListJoin = mouseListFirstFour.join(" ");

document.getElementsByClassName("mouseFourElement").innerHTML = mouseListJoin;


const keyboardList = document.getElementsByClassName("elementKeyboard");
const keyboardArray = [...keyboardList];

const keyboardListFirstFour = keyboardArray.slice(0, 8);

const keyboardListJoin = keyboardListFirstFour.join(" ");

document.getElementsByClassName("keyboardFourElement").innerHTML = keyboardListJoin;

//
const navLinks = document.querySelectorAll('.nav a');

navLinks.forEach(link => {
  link.addEventListener('click', function() {
    // Xóa class "active" khỏi tất cả các link
    navLinks.forEach(link => link.classList.remove('active'));

    // Thêm class "active" cho link được click
    this.classList.add('active');
  });
});
