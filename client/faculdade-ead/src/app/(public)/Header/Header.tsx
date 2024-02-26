import React from "react";
import header from "@/app/(public)/Header/Header.module.sass";
import Link from "next/link";

const Header = () => {
  return (
    <header className={header.header}>
      <h1 className={header.logo}>Logo</h1>
      <div className={header.btns}>
        <ul className={header.btn_ul}>
          <nav>
            <Link href="/">
              <button className={header.btn}>Área do aluno</button>
            </Link>
            <Link href="/">
              <button className={header.btn}>Financeiro</button>
            </Link>
            <Link href="/">
              <button className={header.btn}>Materias</button>
            </Link>
            <Link href="/">
              <button className={header.btn}>Biblioteca vitual</button>
            </Link>
            <Link href="/">
              <button className={header.btn}>Cursos extras</button>
            </Link>
          </nav>
        </ul>
      </div>
    </header>
  );
};

export default Header;
