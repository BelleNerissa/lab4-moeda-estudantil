import React from "react";
import api from "../../services/service";

export default function Transacao() {
  const [formData, setFormData] = React.useState({
    alunoId: null,
    professorId: null,
    valor: null,
    descricao: "",
  });

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
      .post("/transacao/cadastrar/byprofessor", formData)
      .then((res) => alert("Transacao realizada!"));
  }

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card p-4">
            <h1>Realizar Transação</h1>
            <form onSubmit={handleSubmit}>
              <div className="mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Aluno ID"
                  onChange={handleChange}
                  name="alunoId"
                  value={formData.alunoId || ""}
                />
              </div>
              <div className="mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Professor ID"
                  onChange={handleChange}
                  name="professorId"
                  value={formData.professorId || ""}
                />
              </div>
              <div className="mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Valor"
                  onChange={handleChange}
                  name="valor"
                  value={formData.valor || ""}
                />
              </div>
              <div className="mb-3">
                <input
                  type="text"
                  className="form-control"
                  placeholder="Descrição"
                  onChange={handleChange}
                  name="descricao"
                  value={formData.descricao || ""}
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
