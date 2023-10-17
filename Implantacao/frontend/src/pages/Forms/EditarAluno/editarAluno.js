import React from "react";
import { useParams } from "react-router-dom";
import api from "../../../services/service";
import InputMask from "react-input-mask";

export default function EditarAluno() {
  const [formData, setFormData] = React.useState({});
  const [aluno, setAluno] = React.useState({});

  const { id } = useParams();

  function handleChange(event) {
    const { name, value } = event.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value,
    }));
  }

  function handleSubmit(event) {
    event.preventDefault();
    console.log(formData);
    api
      .put(`/aluno/update/id/${id}`, formData)
      .then((res) => (window.location.href = `/aluno/${res.data.id}`));
  }

  React.useEffect(() => {
    api.get(`/aluno/mostrar/id/${id}`).then((res) => {
      setFormData(res.data);
      setAluno(res.data);
    });
  }, [id]);

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card p-5 mb-2" style={{ backgroundColor: "rgba(255, 255, 255, 0.8" }}>
            <h1>Editar {aluno.nome}</h1>
            <form onSubmit={handleSubmit} className="col-md-12">
              <div className="mb-3">
                <label htmlFor="nome" className="form-label">Nome</label>
                <input
                  type="text"
                  className="form-control"
                  id="nome"
                  placeholder="Nome"
                  onChange={handleChange}
                  name="nome"
                  value={formData.nome || ""}
                />
              </div>
              <div className="mb-3">
                <label htmlFor="email" className="form-label">Email</label>
                <input
                  type="email"
                  className="form-control"
                  id="email"
                  placeholder="Email"
                  onChange={handleChange}
                  name="email"
                  value={formData.email || ""}
                />
              </div>
              <div className="mb-3">
                <label htmlFor="senha" className="form-label">Senha</label>
                <input
                  type="password"
                  className="form-control"
                  id="senha"
                  placeholder="Senha"
                  onChange={handleChange}
                  name="senha"
                  value={formData.senha || ""}
                />
              </div>
              <div className="mb-3">
                <label htmlFor="cpf" className="form-label">CPF</label>
                <InputMask
                  type="text"
                  mask="999.999.999-99"
                  maskChar="_"
                  className="form-control"
                  id="cpf"
                  placeholder="CPF"
                  onChange={handleChange}
                  name="cpf"
                  value={formData.cpf || ""}
                />
              </div>
              <div className="mb-3">
                <label htmlFor="rg" className="form-label">RG</label>
                <InputMask
                  type="text"
                  mask="99.999.999-9"
                  maskChar="_"
                  className="form-control"
                  id="rg"
                  placeholder="RG"
                  onChange={handleChange}
                  name="rg"
                  value={formData.rg || ""}
                />
              </div>
              <div className="mb-3">
                <label htmlFor="endereco" className="form-label">Endereço</label>
                <input
                  type="text"
                  className="form-control"
                  id="endereco"
                  placeholder="Endereço"
                  onChange={handleChange}
                  name="endereco"
                  value={formData.endereco || ""}
                />
              </div>
              <button type="submit" className="btn btn-primary btn-block">Submit</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}
