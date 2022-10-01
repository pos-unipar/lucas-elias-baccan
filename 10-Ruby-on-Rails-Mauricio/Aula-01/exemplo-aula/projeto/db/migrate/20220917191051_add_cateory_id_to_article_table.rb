class AddCateoryIdToArticleTable < ActiveRecord::Migration[7.0]
  def change
    add_reference :articles, :categodie, foreign_key: true
  end
end
