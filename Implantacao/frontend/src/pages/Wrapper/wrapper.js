import React from "react";
import Header from "../../Components/Header/header";

const Wrapper = ({ children }) => {
  return (
    <>
      <Header />
      {children}
    </>
  );
};

export default Wrapper;
