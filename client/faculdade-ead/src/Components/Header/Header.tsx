import React from "react";
import header from "@/Components/Header/Header.module.sass";

const Header = () => {
  return (
    <header className={header.header}>
      <h1 className={header.logo}>Logo</h1>
      <div>
        <ul>
          <button className={header.btn}>Área do aluno</button>
          <button className={header.btn}>Financeiro</button>
          <button className={header.btn}>Curso</button>
          <button className={header.btn}>Biblioteca virtual</button>
          <button className={header.btn}>Cursos extras</button>
        </ul>
      </div>
    </header>
  );
};

export default Header;
