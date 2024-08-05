import Swal from 'sweetalert2';

// Khi nút "Thêm vào giỏ hàng" được click
document.getElementById('myButton').addEventListener('click', () => {
  Swal.fire({
    title: 'Đã thêm vào giỏ hàng!',
    text: 'Bạn có thể xem giỏ hàng của mình.',
    icon: 'success',
    confirmButtonText: 'Tiếp tục mua sắm'
  })
});