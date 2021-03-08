<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="../css/profile_page_css.css" />
</head>
<body>
<div class="container">

    <div style="position: fixed; display: none; z-index: 10;" id="passwordLabel" class="card login-form container">
        <div class="card-body">
            <h3 class="card-title text-center">Change password</h3>
            <div class="card-text">
                <form>
                    <div class="form-group">
                        <label>Current password</label>
                        <input type="password" name="current" id="current" class="form-control form-control-sm">
                    </div>
                    <div class="form-group">
                        <label>Your new password</label>
                        <input type="password" name="newPassword" id="new" class="form-control form-control-sm">
                    </div>
                    <div class="form-group">
                        <label>Repeat password</label>
                        <input type="password" name="newPassword2" id="repeat" class="form-control form-control-sm">
                    </div>
                    <button type="button" id="confirm" class="btn btn-primary btn-block submit-btn">Confirm</button>
                    <a href="/profile" class="btn btn-secondary btn-block submit-btn">Cancel</a>
                </form>
            </div>
        </div>
    </div>

    <div class="row gutters">
        <div class="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12">
            <div class="card h-100">
                <div class="card-body">
                    <div class="account-settings">
                        <div class="user-profile">
                            <div class="user-avatar">
                                <c:if test="${user.gender == 'Female'}">
                                    <img src="https://image.flaticon.com/icons/png/512/194/194938.png" alt="Maxwell Admin">
                                </c:if>
                                <c:if test="${user.gender == 'Male'}">
                                    <img src="https://bootdey.com/img/Content/avatar/avatar1.png" alt="Maxwell Admin">
                                </c:if>
                            </div>
                            <h5 class="user-name">${user.firstname} ${user.lastname}</h5>
                            <h6 class="user-email">${user.email}</h6>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
            <div class="card h-100">
                <div class="card-body">
                    <div class="row gutters">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <h6 class="mb-3 text-primary">Personal Details</h6>
                        </div>
                        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                            <div class="form-group">
                                <label>Firstname </label>
                                <input type="text" class="form-control" id="firstName" value="${user.firstname}">
                            </div>
                        </div>
                        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                            <div class="form-group">
                                <label>Lastname</label>
                                <input type="email" class="form-control" id="lastName" value="${user.lastname}">
                            </div>
                        </div>
                        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                            <div class="form-group">
                                <label>Email</label>
                                <input type="text" class="form-control" id="email" value="${user.email}">
                            </div>
                        </div>
                        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                            <div class="form-group">
                                <label>Gender</label>
                                <select class="form-select" id="gender" aria-label="Default select example">
                                    <option value="Male">Male</option>
                                    <option value="Female">Female</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row gutters">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <h6 class="mb-3 text-primary">Another details</h6>
                        </div>
                        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                            <div class="form-group">
                                <label>Username</label>
                                <input type="text" class="form-control" id="username" value="${user.username}">
                            </div>
                            <div class="form-group">
                                <label>Phone</label>
                                <input type="text" class="form-control" id="phone" value="${user.phone}">
                            </div>
                        </div>
                        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                            <button type="submit" id="passwordSettings" class="btn btn-primary btn-block submit-btn">Change Password</button>
                        </div>
                    </div>
                    <div class="row gutters">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="text-right">
                                <a href="/profile" id="cancel" name="submit" class="btn btn-secondary">Cancel</a>
                                <button type="button" id="update" name="submit" class="btn btn-primary">Update</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="../js/profile_page_js.js"></script>
</body>
</html>