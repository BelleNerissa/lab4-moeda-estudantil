import React from "react";
import api from "../../services/service";

export default function CadastrarEmpresa() {
  const [formData, setFormData] = React.useState({
    cnpj: "",
    saldo: 0,
  });

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
      .post("/empresa/cadastrar", formData)
      .then((res) => (window.location.href = `/empresa/${res.data.id}`));
    console.log(formData);
  }

  return (
    <>
      <h1>Cadastrar Empresa</h1>
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
