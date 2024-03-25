import type { Metadata } from "next";
import { Montserrat } from "next/font/google";
import "./global.scss";

const montserrat = Montserrat({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "FT-BA",
  description: "Faculdade de Tecnologia da Bahia",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body className={montserrat.className}>{children}</body>
    </html>
  );
}
