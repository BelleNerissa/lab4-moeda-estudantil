import React from "react";
import { useParams } from "react-router-dom";
import api from "../../services/service";

export default function EditarEmpresa() {
  const [formData, setFormData] = React.useState({
    cnpj: "",
    saldo: 0,
  });

  const { id } = useParams();

  function handleChange(event) {
    const { name, value, type, checked } = event.target;
    setFormData((prevFormData) => {
      return {
        ...prevFormData,
        [name]: type === "checkbox" ? checked : value,
      };
    });
  }

  function handleSubmit(event) {
    event.preventDefault();
    api
      .put(`/update/id/${id}`, formData)
      .then((res) => (window.location.href = `/empresa/${res.data.id}`));
    console.log(formData);
  }

  React.useEffect(() => {
    api.get(`/empresa/mostrar/id/${id}`).then((res) => setFormData(res.data));
  }, [id]);

  return (
    <>
      <h1>Editar Empresa</h1>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="CNPJ"
          onChange={handleChange}
          name="cnpj"
          value={formData.cnpj}
        />
        <button>Submit</button>
      </form>
    </>
  );
}
