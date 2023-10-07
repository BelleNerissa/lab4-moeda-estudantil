import React from "react";
import api from "../../services/service";

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
      .post("/aluno/cadastrar", formData)
      .then((res) => (window.location.href = `/aluno/${res.data.id}`));
    console.log(formData);
  }

  return (
    <>
      <h1>Cadastrar Aluno</h1>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Nome"
          onChange={handleChange}
          name="nome"
          value={formData.nome}
        />
        <input
          type="email"
          placeholder="Email"
          onChange={handleChange}
          name="email"
          value={formData.email}
        />
        <input
          type="password"
          placeholder="Senha"
          onChange={handleChange}
          name="senha"
          value={formData.senha}
        />
        <input
          type="text"
          placeholder="CPF"
          onChange={handleChange}
          name="cpf"
          value={formData.cpf}
        />
        <input
          type="text"
          placeholder="RG"
          onChange={handleChange}
          name="rg"
          value={formData.rg}
        />
        <input
          type="text"
          placeholder="Endereco"
          onChange={handleChange}
          name="endereco"
          value={formData.endereco}
        />
        <button>Submit</button>
      </form>
    </>
  );
}
