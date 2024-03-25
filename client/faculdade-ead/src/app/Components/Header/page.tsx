import React from "react";
import NextLink from "next/link";

import styles from "@/app/Components/Header/Header.module.sass";

const Header = () => {
  return (
    <header className={styles.header}>
      <h1 className={styles.logo}>Logo</h1>
      <div className={styles.btns}>
        <ul className={styles.btn_ul}>
          <nav>
            <NextLink href="/authenticated/Aluno">
              <button className={styles.btn}>Área do aluno</button>
            </NextLink>
            <NextLink href="/">
              <button className={styles.btn}>Financeiro</button>
            </NextLink>
            <NextLink href="/">
              <button className={styles.btn}>Materias</button>
            </NextLink>
            <NextLink href="/">
              <button className={styles.btn}>Biblioteca vitual</button>
            </NextLink>
            <NextLink href="/">
              <button className={styles.btn}>Cursos extras</button>
            </NextLink>
          </nav>
        </ul>
      </div>
    </header>
  );
};

export default Header;
