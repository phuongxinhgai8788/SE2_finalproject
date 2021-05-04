<%@page contentType="text/html"
        pageEncoding="UTF-8" %>
<%@ taglib prefix="t"
           tagdir="/WEB-INF/tags" %>

<t:auth-template>
    <div class="card card-authentication1 mx-auto my-5">
        <div class="card-body">
            <div class="card-content p-2">
                <div class="text-center">
                    <img src="${pageContext.request.contextPath}/images/logo-icon.png"
                         alt="logo icon" />
                </div>

                <div class="card-title text-uppercase text-center py-3">
                    Sign In
                </div>

                <form action="${pageContext.request.contextPath}/login"
                      method="POST">
                    <div class="form-group">
                        <label for="email">
                            Email
                            <span class="text-danger"> ${email_errmsg}</span>
                        </label>
                        <div class="position-relative has-icon-right">
                            <input type="email"
                                   id="email"
                                   name="email"
                                   class="form-control input-shadow"
                                   autofocus />
                            <div class="form-control-position">
                                <i class="icon-user"></i>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="password">
                            Password
                            <span class="text-danger"> ${password_errmsg}</span>
                        </label>
                        <div class="position-relative has-icon-right">
                            <input type="password"
                                   id="password"
                                   name="password"
                                   class="form-control input-shadow" />
                            <div class="form-control-position">
                                <i class="icon-lock"></i>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <span class="text-danger">${globalErrMsg}</span>
                        <a href="${pageContext.request.contextPath}/reset-password"
                           class="float-right">
                            Reset Password
                        </a>
                    </div>

                    <div class="form-group">
                        <button type="submit"
                                class="btn btn-light btn-block">
                            Sign In
                        </button>
                    </div>

                </form>
            </div>
        </div>
        <div class="card-footer text-center py-3">
            <p class="text-warning mb-0">
                Do not have an account?
                <a href="mailto:aiwpjfp@afiwj.asc"> Contact administrator</a>
            </p>
        </div>
    </div>
</t:auth-template>

