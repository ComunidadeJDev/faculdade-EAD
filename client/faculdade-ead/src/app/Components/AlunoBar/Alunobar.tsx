import React from "react";
import Image from "next/image";
import aluno from "./AlunoBar.module.sass";

const Alunobar = () => {
  return (
    <div className={aluno.userInfo}>
      {/* // user information: name, age, course, bday date */}
      <Image className={aluno.userInfoImage} src="" alt="foto do aluno"></Image>
      <p className="userInfo-data">Israel Hamdan</p>
      <p className="userInfo-data">23</p>
      <p className="userInfo-data">04/09</p>
    </div>
  );
};

export default Alunobar;
