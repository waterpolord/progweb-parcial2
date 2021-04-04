$(document).ready(function ($) {
    var array = [];
    $('#table tbody').on('click', '#ok', function () {
        var curRow = $(this).closest('tr');
        var col0 = curRow.find('td:eq(0)').text();
        var col1 = curRow.find('td:eq(1)').text();
        var col2 = curRow.find('td:eq(2)').text();
        array.push(col0)
        array.push(col1)
        array.push(col2)
        document.form.fullName.value = col0;
        document.form.sector.value = col1;
        document.form.role.value = col2;
    });
});