class CreateCategodies < ActiveRecord::Migration[7.0]
  def change
    create_table :categodies do |t|
      t.string :name

      t.timestamps
    end
  end
end
