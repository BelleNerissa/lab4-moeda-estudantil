import React from "react";
import { useParams } from "react-router-dom";
import api from "../../../services/service";

export default function EditarProfessor() {
  const [formData, setFormData] = React.useState({});
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
    api
      .put(`/professor/update/id/${id}`, formData)
      .then((res) => (window.location.href = `/professor/${res.data.id}`));
  }

  React.useEffect(() => {
    api.get(`/professor/mostrar/id/${id}`).then((res) => setFormData(res.data));
  }, [id]);

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card p-4">
            <h1>Editar {formData.nome}</h1>
            <form onSubmit={handleSubmit}>
              <div className="mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Nome"
                  onChange={handleChange}
                  name="nome"
                  value={formData.nome || ""}
                />
              </div>
              <div className="mb-3">
                <input
                  type="email"
                  className="form-control"
                  placeholder="Email"
                  onChange={handleChange}
                  name="email"
                  value={formData.email || ""}
                />
              </div>
              <div className="mb-3">
                <input
                  type="password"
                  className="form-control"
                  placeholder="Senha"
                  onChange={handleChange}
                  name="senha"
                  value={formData.senha || ""}
                />
              </div>
              <div className="mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="CPF"
                  onChange={handleChange}
                  name="cpf"
                  value={formData.cpf || ""}
                />
              </div>
              <button type="submit" className="btn btn-primary btn-block">
                Submit
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}
