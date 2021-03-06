/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controller.Cidade;

import DAO.CidadeDAO;
import DAO.GenericDAO;
import Models.Cidade;
import Models.Estado;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aluno
 */
@WebServlet(name = "SalvarCidade", urlPatterns = {"/SalvarCidade"})
public class SalvarCidade extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        /*Pega da Tela*/
        String mensagem = null;
        int idEstado = Integer.parseInt(request.getParameter("idEstado"));
        int idCidade = Integer.parseInt(request.getParameter("idCidade"));
        String nomeCidade = request.getParameter("nomeCidade");

        /*Manda pra classe*/
        Cidade cCidade = new Cidade();
        Estado cEstado = new Estado();
        cEstado.setIdEstado(idEstado);
        
        cCidade.setEstado(cEstado);
        
        //cCidade.getEstado().setIdEstado(idEstado);
        cCidade.setIdCidade(idCidade);
        cCidade.setNomeCidade(nomeCidade);
        
        try {
            GenericDAO daoCidade = new CidadeDAO();

            if (cCidade.getIdCidade() == 0 || cCidade.getIdCidade() == null) {
                if (daoCidade.Cadastrar(cCidade)) {
                    mensagem = "Cidade cadastrado com sucesso!";
                } else {
                    mensagem = "Problemas ao cadastrar Estado!";
                }
            } else if (daoCidade.Alterar(cCidade)) {
                mensagem = "Cidade alterado com sucesso!";
            } else {
                mensagem = "Problema ao alterar Cidade!";
            }
            request.setAttribute("Sucesso", mensagem);
            request.getRequestDispatcher("ListarCidade").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("Sucesso", ex.getMessage());
            request.getRequestDispatcher("ListarCidade").forward(request, response);
            ex.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
