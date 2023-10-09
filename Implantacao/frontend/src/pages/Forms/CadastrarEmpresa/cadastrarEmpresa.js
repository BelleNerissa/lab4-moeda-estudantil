import React from "react";
import api from "../../../services/service";
import 'bootstrap/dist/css/bootstrap.min.css'; // Import Bootstrap CSS

export default function CadastrarEmpresa() {
  const [formData, setFormData] = React.useState({
    nome: "",
    email: "",
    senha: "",
    cnpj: "",
    saldo: 0,
  });

  function handleChange(event) {
    const { name, value } = event.target;
    setFormData((prevFormData) => {
      return {
        ...prevFormData,
        [name]: value,
      };
    });
  }

  function handleSubmit(event) {
    event.preventDefault();
    api
      .post("/empresa/cadastrar", formData)
      .then((res) => (window.location.href = `/empresa/${res.data.id}`));
  }

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card p-4" style={{ backgroundColor: "rgba(255, 255, 255, 0.8)" }}>
            <h1 className="text-center">Cadastrar Empresa</h1>
            <form onSubmit={handleSubmit} className="col-md-12">
              <div className="mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Nome"
                  onChange={handleChange}
                  name="nome"
                  value={formData.nome}
                />
              </div>
              <div className="mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="CNPJ"
                  onChange={handleChange}
                  name="cnpj"
                  value={formData.cnpj}
                />
              </div>
              <div className="mb-3">
                <input
                  type="email"
                  className="form-control"
                  placeholder="Email"
                  onChange={handleChange}
                  name="email"
                  value={formData.email}
                />
              </div>
              <div className="mb-3">
                <input
                  type="password"
                  className="form-control"
                  placeholder="Senha"
                  onChange={handleChange}
                  name="senha"
                  value={formData.senha}
                />
              </div>
              <button className="btn btn-primary btn-block">Cadastrar</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}
