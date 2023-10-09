import React from "react";
import { useParams } from "react-router-dom";
import api from "../../../services/service";
import 'bootstrap/dist/css/bootstrap.min.css'; // Import Bootstrap CSS

export default function EditarEmpresa() {
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
    api.put(`/empresa/update/id/${id}`, formData).then((res) => {
      window.location.href = `/empresa/${res.data.id}`;
    });
  }

  React.useEffect(() => {
    api.get(`/empresa/mostrar/id/${id}`).then((res) => {
      setFormData(res.data);
    });
  }, [id]);

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card p-4">
            <h1 className="text-center">Editar Empresa</h1>
            <form onSubmit={handleSubmit} className="card p-12">
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
              <button className="btn btn-primary btn-block">Salvar</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}
