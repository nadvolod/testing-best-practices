// This is a login that exposes implementation details
await loginPage.login(validUser);
// The issue here is that the implementation details of how the login happens are exposed
await authPage.loginWith2FA(validUser);
