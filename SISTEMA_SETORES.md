# Sistema de Setores e Agendamentos - FoodCampus

## 📋 **Resumo**

Foi implementado um sistema completo de agendamento de vendedores em setores fixos da UFRN, substituindo o sistema anterior de localizações variáveis. O sistema permite que vendedores se agendem em setores específicos da universidade e os clientes visualizem em tempo real quais vendedores estão disponíveis em cada local.

## 🗃️ **Estrutura do Banco de Dados**

### Tabelas Criadas

1. **`setores`** - Localizações fixas da UFRN
2. **`agendamentos_vendedor`** - Agendamentos dos vendedores nos setores
3. **`reservas`** - Reservas feitas pelos clientes (preparada para implementação futura)

### Remoções

- ❌ **Tabela `localizacoes`** - Removida conforme solicitado

## 🚀 **Funcionalidades Implementadas**

### Backend (Spring Boot)

1. **Endpoints REST**:
   - `GET /api/v1/setores` - Listar setores ativos
   - `GET /api/v1/setores/{id}/vendedores` - Vendedores por setor
   - `POST /api/v1/setores/agendamento` - Agendar vendedor (requer ROLE_VENDEDOR)
   - `GET /api/v1/setores/meus-agendamentos` - Meus agendamentos (requer ROLE_VENDEDOR)

2. **Validações**:
   - Conflitos de horário para o mesmo vendedor
   - Validação de datas (não permite agendamento no passado)
   - Verificação de existência de setor e vendedor

3. **Segurança**:
   - Endpoints públicos para consulta
   - Endpoints protegidos para agendamento (apenas vendedores)

### Frontend (React/Next.js)

1. **Hook `useSetores`**:
   - Gerenciamento de estado dos setores
   - Funções para agendamento e consulta

2. **Componente `MapaSetores`**:
   - Mapa interativo com setores da UFRN
   - Exibição de vendedores em tempo real
   - Filtro por data

3. **Componente `AgendamentoSetor`**:
   - Formulário para vendedores se agendarem
   - Validações no frontend
   - Feedback visual de sucesso/erro

## 📱 **Como Usar**

### Para Vendedores

1. **Fazer Login** como vendedor
2. **Acessar Home do Vendedor** - verá o formulário de agendamento
3. **Selecionar Setor** - escolher onde deseja vender
4. **Definir Data e Horário** - quando estará disponível
5. **Adicionar Observações** - informações sobre produtos/promoções
6. **Confirmar Agendamento** - sistema validará conflitos

### Para Clientes

1. **Acessar Home do Cliente** - verá o mapa interativo
2. **Visualizar Setores** - marcadores no mapa da UFRN
3. **Clicar nos Marcadores** - ver vendedores disponíveis
4. **Filtrar por Data** - vendedores para data específica
5. **Ver Informações** - contato, horário, produtos do vendedor

## 🔧 **Configuração**

### 1. Banco de Dados

Execute o script SQL para popular os setores:

```sql
-- Arquivo: foodcampus_backend/src/main/resources/sql/setores_inicial.sql
INSERT INTO setores (nome, sigla, latitude, longitude, descricao, endereco, ativo, data_criacao) VALUES
('Centro de Tecnologia', 'CT', -5.837000, -35.204100, 'Centro de cursos de engenharia e tecnologia', 'Campus Central UFRN', true, NOW()),
('Centro de Ciências Sociais Aplicadas', 'CCSA', -5.838000, -35.202500, 'Centro de administração, direito e serviço social', 'Campus Central UFRN', true, NOW()),
-- ... outros setores
```

### 2. Backend

As configurações de segurança já foram atualizadas para incluir os novos endpoints.

### 3. Frontend

Os componentes já foram integrados nas páginas Home existentes.

## 🎯 **Vantagens do Sistema**

1. **✅ Simplicidade** - Sem complexidade de múltiplas localizações
2. **✅ Consistência** - Pontos fixos conhecidos pelos usuários
3. **✅ Flexibilidade** - Vendedores podem escolher qualquer setor
4. **✅ Tempo Real** - Visualização instantânea de disponibilidade
5. **✅ Escalabilidade** - Fácil adicionar novos setores
6. **✅ UX Otimizada** - Interface intuitiva e responsiva

## 🧪 **Testes**

### Testar Endpoints (Postman/curl)

```bash
# Listar setores
GET http://localhost:8080/api/v1/setores

# Vendedores por setor
GET http://localhost:8080/api/v1/setores/1/vendedores?data=2024-01-15

# Agendar vendedor (requer token)
POST http://localhost:8080/api/v1/setores/agendamento
{
  "setorId": 1,
  "dataInicio": "2024-01-15T08:00:00",
  "dataFim": "2024-01-15T17:00:00",
  "observacoes": "Lanches e bebidas"
}
```

## 📋 **Próximos Passos**

1. **Sistema de Reservas** - Implementar reservas de clientes
2. **Notificações** - Avisar sobre novos agendamentos
3. **Relatórios** - Dashboard para vendedores
4. **Avaliações** - Sistema de feedback
5. **Integração com Produtos** - Vincular produtos aos agendamentos

## 🐛 **Troubleshooting**

### Problemas Comuns

1. **Erro 403 ao agendar**: Verifique se o usuário tem ROLE_VENDEDOR
2. **Mapa não carrega**: Verifique conexão com internet (CDN do Leaflet)
3. **Setores não aparecem**: Execute o script SQL de população
4. **Conflito de horário**: Verifique se já existe agendamento no horário

### Logs Úteis

```bash
# Backend
tail -f logs/application.log | grep "SetorService"

# Frontend
# Abra o DevTools do navegador e veja o console
```

## 🤝 **Contribuição**

O sistema foi desenvolvido seguindo as melhores práticas de Clean Architecture e está pronto para extensões futuras. A estrutura modular permite fácil manutenção e adição de novas funcionalidades.

---

**Sistema implementado com sucesso! 🎉** 