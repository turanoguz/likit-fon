export default function Logout() {
  localStorage.removeItem("token");
  window.location.pathname = "/";

  return null;
}
