// Función para validar que el campo de texto no esté vacío antes de enviar el formulario
function validateForm(event) {
    // Obtener el valor del campo de texto
    const courseName = document.querySelector('input[type="text"]').value;

    // Si el campo está vacío, mostrar un mensaje de advertencia y evitar el envío del formulario
    if (!courseName.trim()) {
        alert('Course name cannot be empty!');
        event.preventDefault(); // Evitar el envío del formulario
    } else {
        // Si el campo tiene valor, mostrar el mensaje de éxito
        showSuccessMessage();
    }
}

// Función para mostrar el mensaje de éxito
function showSuccessMessage() {
    const successMessage = document.getElementById('success-message');
    successMessage.style.display = 'block';  // Mostrar el mensaje de éxito
}

// Agregar un evento para la validación cuando el formulario se envíe
document.addEventListener('DOMContentLoaded', function () {
    const form = document.querySelector('#course-form');
    form.addEventListener('submit', validateForm);
});
