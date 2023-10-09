export const isAuthenticated = () => {
  return !!window.localStorage.getItem("id");
};

export const loginAuth = (id, roleId) => {
  window.localStorage.setItem("id", id);
  window.localStorage.setItem("roleId", roleId);
};

export const logout = () => {
  window.localStorage.clear();
  window.location.reload(true);
};

export const getId = () => window.localStorage.getItem("id");
export const getRoleId = () => window.localStorage.getItem("roleId");

export const isAluno = () => {
  const roleId = window.localStorage.getItem("roleId");
  return roleId === "1" ? true : false;
};

export const isProfessor = () => {
  const roleId = window.localStorage.getItem("roleId");
  return roleId === "2" ? true : false;
};

export const isEmpresa = () => {
  const roleId = window.localStorage.getItem("roleId");
  return roleId === "3" ? true : false;
};

export const getType = () => {
  const roleId = window.localStorage.getItem("roleId");
  if (roleId === "1") {
    return "aluno";
  } else if (roleId === "2") {
    return "professor";
  } else {
    return "empresa";
  }
};
