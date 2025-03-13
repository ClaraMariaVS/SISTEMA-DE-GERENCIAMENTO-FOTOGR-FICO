<%-- 
    Document   : index
    Created on : 7 de abr. de 2023, 19:14:44
    Author     : Alex
--%>

<%@page contentType="text/html; utf-8" pageEncoding="UTF-8"
        import="model.PerfilDAO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width,
              initial-scale=1.0, shrink-to-fit=no">
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/nav.css" type="text/css">
        <link rel="stylesheet" href="css/styles.css" type="text/css">
        <link rel="stylesheet" href="webfonts/css/all.css" type="text/css">
        <link rel="stylesheet" href="sweetalert2/sweetalert2.min.css"  type="text/css">
        <style>
            body{
                background-image: url("imagens/abstract-grey.jpg");
            }

            h3{
                padding-bottom: 40px;
                padding-top: 40px;
            }
        </style>
        <title>Cadastro de Usuários</title>
    </head>
    <body>
        <div id="container">
            <div id="header">
                <jsp:include page="template/banner.jsp"></jsp:include>
            </div>
            <div id="menu">
                <jsp:include page="template/menu.jsp"></jsp:include>
            </div>
            <div id="conteudo">
              
                
               
                <form action="gerenciarUsuario" method="POST">
                    
                    <h3 class="text-center mt-5" style="padding-top:20px">
                        Página de Cadastro
                    </h3>
                    <input type="hidden" id="idusuario" name="idUsuario"
                           value ="${usuario.idUsuario}">
                    
                    <div class="form-group row mt-5 offset-md-3">
                        <label for="idnome"
                               class="col-md-2 col-form-label-lg">Nome</label>
                        <div class="col-md-5">
                            <input type="text" id="idnome" name="nome"
                                   class="form-control" value="${usuario.nome}">
                        </div>
                    </div>
                    <div class="form-group row mt-5 offset-md-3">
                        <label for="idlogin"
                               class="col-md-2 col-form-label-lg">Login
                        </label>
                        <div class="col-md-5">
                            <input type="text" id="idlogin" name="login"
                                   class="form-control" value="${usuario.login}">
                        </div>
                        
                        
                    </div>
                      <div class="form-group row mt-5 offset-md-3">
                        <label for="idsenha"
                               class="col-md-2 col-form-label-lg">Senha
                        </label>
                        <div class="col-md-5">
                            <input type="password" id="idsenha" name="senha"
                                   class="form-control" value="${usuario.senha}">
                        </div>
                    </div>
                    <div class="form-group row offset-md-3 mt-3">
                        <label for="idperfil"
                               class="col-md-2 col-form-label-lg">Perfil</label>
                        <div class="col-md-5">
                            <select id="idperfil" name="idPerfil"
                                    class="form-control mt-2">
                            <jsp:useBean class="model.PerfilDAO" id="pdao">
                                <c:forEach items="${pdao.lista}" var="p">
                                    <option value="${p.idPerfil}"
                                        <c:if test="${p.idPerfil == usuario.perfil.idPerfil}">
                                            selected
                                        </c:if>>${p.nome}
                                    </option>
                                </c:forEach>
                                
                            </jsp:useBean>
                                
                            </select>
                            
                        </div>
                        
                        
                    </div>
                    
                    
                    <div class="form-group row  offset-md-3">
                        <label for="idstatus"
                               class="col-md-2 col-form-label-lg">Status
                        </label>
                        <div class="col-md-5">
                            <select id="idstatus" name="status"
                                    class="form-control mt-2">
                                <option value="">Escolha uma opção</option>
                                <option value="1"
                                    <c:if test="${usuario.status == 1}">
                                    selected=""</c:if>>Ativado
                                </option>
                                <option value="0"
                                    <c:if test="${usuario.status == 0}">
                                        selected=""</c:if>>Desativado
                                </option>
                                
                            </select>
                        </div>
                                          
                    </div>
                    <div class="d-md-flex justify-content-md-end mt-5">
                        <button class="btn btn-primary btn-md mr-4">
                            Gravar&nbsp;<i class="fa-solid fa-floppy-disk"></i>
                        </button>
                        <a href="gerenciarUsuario?acao=listar"
                           class="btn btn-danger btn-md mr-3"
                           role="button">
                            Voltar&nbsp;<i class="fa-solid fa-cirle-left"></i>
                            
                        </a>
                        
                    </div>
                    
                    
                    
                </form>
               
            </div>
       
        <!-- JQuery.js -->
        <script src="js/jquery-3.6.0.min.js"></script>
        
        <!-- Popper via cdn -->
         <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha512-Ua/7Woz9L5O0cwB/aYexmgoaD7lw3dWe9FvXejVdgqu71gRog3oJgjSWQR55fwWx+WKuk8cl7UwA1RS6QCadFA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <!-- Bootstrap.js -->
        <script src="js/bootstrap.min.js"></script>
        <script src="sweetalert2/sweetalert2.min.js"></script>
        
        <%
            if(request.getAttribute("msg") != null){
               %>
        <script>
            $(document).ready(function(){
                Swal.fire({
                    position : 'top-center',
                    icon: 'error',
                    title: 'Atenção',
                    text: '<%= request.getAttribute("msg") %>',
                    showConfirmButton : false,
                    timer: 4000
                    
                    
                });
            });
            
            
        </script>
        
        <%  
            }
        
        %>
    </body>
</html>