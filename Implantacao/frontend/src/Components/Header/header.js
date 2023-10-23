import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import {
  isAuthenticated,
  isEmpresa,
  logout,
  getType,
  getId,
} from "../../services/auth";
import "./styles.css";

const Header = () => {
  const [authenticated, setAuthenticated] = useState(isAuthenticated());

  const checkAuthentication = () => {
    setAuthenticated(isAuthenticated());
  };

  // Adicione o ouvinte quando o componente for montado.

  useEffect(() => {
    // Defina um ouvinte para o estado de autenticação.
    // Isso garantirá que a função isAuthenticated() seja verificada continuamente.
    checkAuthentication();
  });


  return (
    <>
      <header className="navbar navbar-expand-lg navbar-light" style={{ backgroundColor: "rgba(112, 129, 136, 0.8)" }}>
        <div className="container">
          <Link className="navbar-brand" to="/">
            <h2 className="text-warning">🥇SME🥇</h2>
          </Link>
          {authenticated && (
            <div className="ml-auto">
              <ul className="navbar-nav">
                <li className="nav-item">
                  <span className="nav-link text-light">
                    Logado como: <strong>{getType()}</strong>
                  </span>
                </li>
                <li className="nav-item">
                  <Link className="nav-link text-light" to="/dashboard">
                    Listagem
                  </Link>
                </li>
                {isEmpresa() ? (
                  <li className="nav-item">
                    <Link className="nav-link text-white" to={`/empresa/${getId()}`}>
                      Minha Conta
                    </Link>
                  </li>
                ) : ( <li className="nav-item">
                  <Link
                    className={`nav-link text-light ${isEmpresa() ? "ml-2" : "" // Add ml-2 margin to balance spacing
                      }`}
                    to={`/${getType()}/${getId()}`}
                  >
                    Minha Conta
                  </Link>
                </li>)}
                <li className="nav-item">
                  <button
                    className="btn btn-danger ml-2" // Add ml-2 margin to balance spacing
                    onClick={() => {
                      logout();
                    }}
                  >
                    Sair
                  </button>
                </li>
              </ul>
            </div>
          )}
        </div>
      </header>
    </>
  );
};

export default Header;
