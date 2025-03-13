<header>
    <nav class="navbar navbar-expand-lg navbar-light">
    
        <p class="nav-link nav-brand" href="#">
        <img src="<%= request.getContextPath()%>/imagens/logonovatrans.png" 
     width="100%" height="85px">
        </p>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav ml-lg-auto">
      <li class="nav-item active">
        <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
      </li>
       <li class="nav-item">
        <a class="nav-link" href="gerenciarMenu?acao=listar">Menus</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="gerenciarPerfil?acao=listar">Perfis</a>
      </li> 
       <li class="nav-item">
        <a class="nav-link" href="gerenciarUsuario?acao=listar">Usuários</a>
      </li>
       <li class="nav-item">
        <a class="nav-link" href="gerenciarCliente?acao=listar">Clientes</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="gerenciarServico?acao=listar">Serviços</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="gerenciarAgendamento?acao=listar">Agendamento</a>
      </li>
    </ul>
  </div>
</nav>
</header>
