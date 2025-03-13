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
        <meta charset ="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, 
              shrink-to-fit=no">
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/nav.css" type="text/css">
        <link rel="stylesheet" href="webfonts/css/all.css" type="text/css">
        <link rel="stylesheet" href="datatables/css/dataTables.bootstrap4.min.css" type="text/css">
        <title>Listar Menus</title>
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
                <div class="col-12">
                    <h3 class="text-center mt-5" style="padding-top: 90px">Listagem de Menus</h3>
                    
                </div>
                <div class="col-12" style="padding-bottom: 15px" >
                    <a href="cadastrarMenu.jsp"
                       class="btn btn-primary btn-md"
                       role="button">Cadastrar Menu&nbsp;
                       <i class="fa-solid fa-floppy-disk"></i>
                    </a>
                </div>
                <div class="container">
                    <table class="table table-hover" id="listarMenus">
                        <thead>
                            <tr>
                                <th>Código</th>
                                <th>Nome</th>
                                <th>Link</th>
                                <th>Exibir</th>
                                <th>Status</th>
                                <th>Ações</th>
                                
                            </tr>
                        </thead>
                        <c:forEach items="${menus}" var="m">
                        <tbody>
                            <tr>
                                <td>${m.idMenu}</td>
                                <td>${m.nome}</td>
                                <td>${m.link}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${m.exibir == 1}">
                                            Sim
                                        </c:when>
                                        <c:otherwise>
                                            Não
                                         </c:otherwise>
                                        
                                    </c:choose>
                                    
                                    
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${m.status == 1}">
                                            Ativado
                                        </c:when>
                                        <c:otherwise>
                                            Desativado
                                        </c:otherwise>
                                        
                                    </c:choose>
                                
                                  </td>
                                  <td>
                                      <a href="gerenciarMenu?acao=alterar&idMenu=${m.idMenu}"
                                         class="btn btn-info btn-md" role="button">
                                          Alterar&nbsp;<i class="fa-solid fa-pen-to-square"></i>
                                          
                                      </a>
                                         <script type="text/javascript">
                                             function confirmDesativar(id, nome){
                                                 if(confirm('Deseja desativar o menu ' + nome + '?')){
                                                     location.href="gerenciarMenu?acao=desativar&idMenu="+id;
                                                 }
                                             }
                                             
                                             function confirmAtivar(id, nome){
                                                 if(confirm('Deseja ativar o menu ' + nome + '?')){
                                                     location.href="gerenciarMenu?acao=ativar&idMenu="+id;
                                                 }
                                             }
                                         </script>
                                        
                                      <c:choose>
                                          <c:when test="${m.status == 1}">
                                              <button class="btn btn-danger btn-sm" 
                                                      onclick="confirmDesativar('${m.idMenu}', '${m.nome}')">
                                                  Desativar&nbsp;
                                                  <i class="fa-solid fa-user-lock"></i>
                                              </button>
                                          </c:when>
                                          <c:otherwise>
                                              <button class="btn btn-success btn-sm" 
                                                      onclick="confirmAtivar('${m.idMenu}', '${m.nome}')">
                                                  Ativar&nbsp;
                                                  <i class="fa-solid fa-user-shield"></i>
                                              </button>
                                          </c:otherwise>
                                      </c:choose>
                                      
                                  </td>
                            </tr>
                         </c:forEach>   
                        </tbody>
                        
                    </table>
                    
                    
                </div>
                
             
            </div>
            
            
        </div>
       
        <!-- JQuery.js -->
        <script src="js/jquery-3.6.0.min.js"></script>
        
        <!-- Popper via cdn -->
         <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha512-Ua/7Woz9L5O0cwB/aYexmgoaD7lw3dWe9FvXejVdgqu71gRog3oJgjSWQR55fwWx+WKuk8cl7UwA1RS6QCadFA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <!-- Bootstrap.js -->
        <script src="js/bootstrap.min.js"></script>
        <!--jquery.datatables.min.js -->
        <script src="datatables/js/jquery.dataTables.min.js"></script>
        <!--dataTables.bootstrap4.min.js -->
        <script src="datatables/js/dataTables.bootstrap4.min.js"></script>
        
        <script type="text/javascript">
             $(document).ready(function () {
                $("#listarMenus").dataTable({
                    "bJQueryUI": true,
                    "lengthMenu": [[5, 10, 20, 25, -1], [5, 10, 20, 25, "Todos"]],
                        "oLanguage": {
                            "sProcessing": "Processando..",
                            "sLengthMenu": "Mostrar _MENU_ registros",
                            "sZeroRecords": "Não foram encontrados resultados",
                            "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
                            "sInfoEmpty": "Mostrando de 0 até 0 de 0 registros",
                            "sInfoFiltered": "",
                            "sInfoPostFix": "",
                            "sSearch": "Pesquisar",
                            "sUrl": "",
                        "oPaginate": {
                            "sFirst": "Primeiro",
                            "sPrevious": "Anterior",
                            "sNext": "Próximo",
                            "sLast": "último"
                            }
                        }
                    });
                }); 
            </script>
    </body>
</html>

