import React from "react";
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
  return (
    <>
      <header className="navbar navbar-expand-lg navbar-light bg-secondary">
        <div className="container">
          <Link className="navbar-brand" to="/">
            <h2 className="text-light">Sistema de Moedas Estudantil</h2>
          </Link>
          {isAuthenticated() && (
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
                {isEmpresa() && (
                  <li className="nav-item">
                    <Link className="nav-link text-white" to={`/empresa/${getId()}`}>
                      Minha Conta
                    </Link>
                  </li>
                )}
                <li className="nav-item">
                  <Link
                    className={`nav-link text-light ${
                      isEmpresa() ? "ml-2" : "" // Add ml-2 margin to balance spacing
                    }`}
                    to={`/${getType()}/${getId()}`}
                  >
                    Minha Conta
                  </Link>
                </li>
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
