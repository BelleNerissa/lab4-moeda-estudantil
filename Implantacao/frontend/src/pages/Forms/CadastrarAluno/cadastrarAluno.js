import React from "react";
import api from "../../../services/service";
import 'bootstrap/dist/css/bootstrap.min.css'; // Import Bootstrap CSS
import InputMask from "react-input-mask";

export default function CadastrarAluno() {
  const [formData, setFormData] = React.useState({
    nome: "",
    email: "",
    senha: "",
    cpf: "",
    rg: "",
    endereco: "",
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
    api.post("/aluno/cadastrar", formData).then((res) => {
      res.data.id >= 1
        ? (window.location.href = `/aluno/${res.data.id}`)
        : alert("FALHA");
    });
  }

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card p-4" style={{ backgroundColor: "rgba(255, 255, 255, 0.8)" }}>
            <h1 className="text-center">Cadastrar Aluno</h1>
            <form onSubmit={handleSubmit}>
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
              <div className="mb-3">
                <InputMask
                  type="text"
                  mask="999.999.999-99"
                  maskChar="_"
                  className="form-control"
                  placeholder="CPF"
                  onChange={handleChange}
                  name="cpf"
                  value={formData.cpf}
                />
              </div>
              <div className="mb-3">
                <InputMask
                  type="text"
                  mask="99.999.999-9"
                  maskChar="_"
                  className="form-control"
                  placeholder="RG"
                  onChange={handleChange}
                  name="rg"
                  value={formData.rg}
                />
              </div>
              <div className="mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Endereco"
                  onChange={handleChange}
                  name="endereco"
                  value={formData.endereco}
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
