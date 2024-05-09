$(document).ready(function () {
    $('#deleteConfirmModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        var accountId = button.data('account-id');
        var deleteUrl = '/account/delete/' + accountId;

        var modal = $(this);
        modal.find('.btn-danger').attr('href', deleteUrl);
    });
});
