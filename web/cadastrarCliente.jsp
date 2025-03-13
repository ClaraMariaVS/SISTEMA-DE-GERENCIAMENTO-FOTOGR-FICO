<%-- 
    Document   : index
    Created on : 7 de abr. de 2023, 19:14:44
    Author     : Alex
--%>

<%@page contentType="text/html; utf-8" pageEncoding="UTF-8"%>
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
        <style>
            form{
                background-image: url("imagens/abstract-grey.jpg")
                    
            }
            h3{
                padding-bottom: 40px;
                padding-top: 40px;
            }
        </style>
        <script type="text/javascript">
             var swal1 = () => swal("Cadastro concluído.", "Cliente cadastrado com sucesso!", "success");
        </script>
        
        
        <title>Cadastro de Clientes</title>
    </head>
    <body>
        <div id="container">
            <div id="header"> 
                <jsp:include page="template/banner.jsp"></jsp:include>
            </div>
            <div id="menu"> 
                <jsp:include page="template/menu.jsp"></jsp:include>
            </div>
            <div id="conteudo" class="bg-background"> 
                <form action="gerenciarCliente" method="POST">
                    <h3 class="text-center">Cadastro de Cliente</h3>
                    
                    <input type="hidden" id="idCliente" name="idCliente" 
                           value="${cliente.idCliente}">
                    <div class="form-group row offset-md-2">
                        <label for="idnome" 
                               class="col-md-3 col-form-label-lg">Nome</label>
                        <div class="col-md-5">
                            <input type="text" name="nome" id="idnome" 
                                   class="form-control" value="${cliente.nome}">
                        </div>
                    </div>
                        
                    <div class="form-group row offset-md-2">
                        <label for="idcpf" 
                               class="col-md-3 col-form-label-lg">CPF</label>
                        <div class="col-md-5">
                            <input type="text" name="cpf" id="idcpf" 
                                   class="form-control" value="${cliente.cpf}">
                        </div>
                    </div>
                        
                    <div class="form-group row offset-md-2">
                        <label for="idemail" 
                               class="col-md-3 col-form-label-lg">Email</label>
                        <div class="col-md-5">
                            <input type="text" name="email" id="idemail" 
                                   class="form-control" value="${cliente.email}">
                        </div>
                    </div>
                        
                    <div class="form-group row offset-md-2">
                        <label for="idendereco" 
                               class="col-md-3 col-form-label-lg">Endereço</label>
                        <div class="col-md-5">
                            <input type="text" name="endereco" id="idendereco" 
                                   class="form-control" value="${cliente.endereco}">
                        </div>
                    </div>
                        
                    <div class="form-group row offset-md-2">
                        <label for="idtelefone" 
                               class="col-md-3 col-form-label-lg">Telefone</label>
                        <div class="col-md-5">
                            <input type="text" name="telefone" id="idtelefone" 
                                   class="form-control" value="${cliente.telefone}">
                        </div>
                    </div>
                    
                    <div class="form-group row offset-md-2">
                        <label for="iddata" 
                               class="col-md-3 col-form-label-lg">Data de Cadastro</label>
                        <div class="col-md-5">
                            <input type="date" name="dataCadastro" id="iddata" 
                                   class="form-control" value="${cliente.dataCadastro}">
                        </div>
                    </div>
                    
                    <div class="form-group row offset-md-2">
                        <label for="idstatus" 
                               class="col-md-3 col-form-label-lg">Status</label>
                        <div class="col-md-5">
                            <select id="idstatus" name="status" class="form-control-lg">
                            <option value="#">Escolha uma opção</option>
                            <option value="1" <c:if test="${cliente.status == 1}"> 
                                    selected=""</c:if>>Ativado</option>
                            <option value="0" <c:if test="${cliente.status == 0}"> 
                                    selected=""</c:if>>Desativado</option>
                            </select>
                        </div>
                    </div>
                    <div class="d-md-flex justify-content-md-end mr-3 mt-5">
         
                        <button onclick="swal1()" class="btn btn-primary btn-md mr-3">
                            Gravar&nbsp;<i class="fa-solid fa-floppy-disk"></i>
                        </button>
                        <a href="gerenciarCliente?acao=listar" class="btn btn-info btn-md" role="button">Voltar&nbsp;<i class="fa-solid fa-circle-left"></i></a>
                    </div>
                </form>
            </div>
            
        </div>
    
    
        <!-- JQuery.js -->
        <script src="js/jquery-3.6.0.min.js"></script>
        <!-- Popper via cdn -->
         <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha512-Ua/7Woz9L5O0cwB/aYexmgoaD7lw3dWe9FvXejVdgqu71gRog3oJgjSWQR55fwWx+WKuk8cl7UwA1RS6QCadFA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <!-- Bootstrap.js -->
        <script src="js/bootstrap.min.js"></script>
        <!--<!-- SweetAlert -->
        <script src="js/sweetalert.min.js"></script>
    
    </body>
</html>