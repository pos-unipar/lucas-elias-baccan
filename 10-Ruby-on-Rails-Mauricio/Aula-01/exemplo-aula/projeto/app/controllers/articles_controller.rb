class ArticlesController < ApplicationController
    def index
        @title = "Lista de Artigos"
        @articles = Article.all
    end

    def show
        begin
            @article = Article.find(params[:id])
        rescue => exception
            redirect_to articles_path
        end
    end

    def new
        @article = Article.new
    end

    def create
        @article = Article.new(article_params)
    
        if @article.save
          redirect_to @article
        else
          render :new, status: :unprocessable_entity
        end
    end
    
    private
        def article_params
            params.require(:article).permit(:title, :body)
        end
end